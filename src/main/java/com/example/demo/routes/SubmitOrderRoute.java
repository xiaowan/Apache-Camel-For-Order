package com.example.demo.routes;

import com.example.demo.components.order.OrderInputParamConvertComponent;
import com.example.demo.components.order.ProductComponent;
import com.example.demo.components.order.ShopComponent;
import com.example.demo.components.result.PreOrderResultComponent;
import com.example.demo.components.result.SubmitOrderResultComponent;
import com.example.demo.components.saveorder.AfterSubmitOrderComponent;
import com.example.demo.components.saveorder.PreLockFundComponent;
import com.example.demo.components.saveorder.PreLockStockComponent;
import com.example.demo.components.saveorder.SaveOrderComponent;
import com.example.demo.components.shipping.ShippingComponent;
import com.example.demo.params.pre.PreCartOrder;
import com.example.demo.params.pre.PreOrder;
import com.example.demo.params.submit.*;
import com.example.demo.routes.predicate.ItemCheckPredicate;
import com.example.demo.routes.predicate.ShippingPredicate;
import com.example.demo.routes.predicate.UseDiscountPredicate;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 预订单/下单核心流程
 *
 * @author licong
 * @date 2021/2/4 下午7:38
 */
@Component
public class SubmitOrderRoute extends RouteBuilder {

    @Autowired
    private OrderInputParamConvertComponent orderInputParamConvertComponent;

    @Autowired
    private ProductComponent productComponent;

    @Autowired
    private ShippingComponent shippingComponent;

    @Autowired
    private ShopComponent shopComponent;

    @Autowired
    private PreOrderResultComponent preOrderResultComponent;

    @Autowired
    private SubmitOrderResultComponent submitOrderResultComponent;

    @Autowired
    private SaveOrderComponent saveOrderComponent;

    @Autowired
    private PreLockFundComponent preLockFundComponent;

    @Autowired
    private PreLockStockComponent preLockStockComponent;

    @Autowired
    private AfterSubmitOrderComponent afterSubmitOrderComponent;

    @Override
    public void configure() throws Exception {

        /**聚合下单入参,目前该流程未开启事务，如需开启，需spring配置事务管理器*/
        from("direct:aggregationInputOrderParam")
            // .transacted()
            .choice()
                .when(bodyAs(PreCartOrder.class))
                .when(bodyAs(SubmitCartOrder.class))
                    .bean(orderInputParamConvertComponent, "generateSubmitCartItemInfo")
                .otherwise()
                    .bean(orderInputParamConvertComponent, "generateSubmitItemInfo")
            .end()
            /**调用商品中心，组装商品详情*/
            .bean(productComponent)
            /**调用商家中心，获取店铺详情，按店铺分组item*/
            .bean(shopComponent)
            .enrich("direct:itemCheck")
            //.bean("按照商家维度聚合itemDetail")
            /**营销流程*/
            .enrich("direct:useDiscount")
            // 获取运费，均摊运费
            .filter().method(ShippingPredicate.class).bean(shippingComponent).end()
            //.bean("拆单")
            /**如果是预订单，直接返回，如果是提交订单，执行后续流程*/
            .choice()
                .when(header("operationType").isEqualTo(PreOrder.class.getSimpleName()))
                    .bean(preOrderResultComponent)
                .otherwise().enrich("direct:saveOrder")
            .end();

        /**下单校验，包含itemDetail有效性，收货地址有效性等*/
        from("direct:itemCheck")
            .routingSlip().method(ItemCheckPredicate.class)
            .bean(productComponent, "checkInvalidItemDetail");

        /**营销组件*/
        from("direct:useDiscount")
            .routingSlip().method(UseDiscountPredicate.class);

        /**提交订单后续流程*/
        from("direct:saveOrder")
            .bean(saveOrderComponent)
            .bean(preLockFundComponent)
            .choice()
                .when(new Predicate() {
                    @Override
                    public boolean matches(Exchange exchange) {
                        /**判断是否需要扣库存*/
                        return false;
                    }
                }).bean(preLockStockComponent)
            .end()
            .bean(afterSubmitOrderComponent)
            .bean(submitOrderResultComponent);
    }
}
