package com.example.demo.routes;

import com.example.demo.components.fund.UnLockFundComponent;
import com.example.demo.components.itemcheck.InvalidItemCheckComponent;
import com.example.demo.components.order.OrderInputParamConvertComponent;
import com.example.demo.components.order.ProductComponent;
import com.example.demo.components.order.ShopComponent;
import com.example.demo.components.result.PreOrderResultComponent;
import com.example.demo.components.result.SubmitOrderResultComponent;
import com.example.demo.components.result.SumInfoComponent;
import com.example.demo.components.saveorder.AfterSubmitOrderComponent;
import com.example.demo.components.fund.LockFundComponent;
import com.example.demo.components.stock.LockStockComponent;
import com.example.demo.components.saveorder.SaveOrderComponent;
import com.example.demo.components.shipping.ShippingComponent;
import com.example.demo.components.splitorder.SplitOrderComponent;
import com.example.demo.components.stock.UnLockStockComponent;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ApiException;
import com.example.demo.params.pre.PreCartOrder;
import com.example.demo.params.pre.PreOrder;
import com.example.demo.params.submit.*;
import com.example.demo.routes.predicate.ItemCheckPredicate;
import com.example.demo.routes.predicate.ShippingPredicate;
import com.example.demo.routes.predicate.StockPredicate;
import com.example.demo.routes.predicate.UseDiscountPredicate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
    private LockFundComponent lockFundComponent;

    @Autowired
    private LockStockComponent lockStockComponent;

    @Autowired
    private AfterSubmitOrderComponent afterSubmitOrderComponent;

    @Autowired
    private SplitOrderComponent splitOrderComponent;

    @Autowired
    private InvalidItemCheckComponent invalidItemCheckComponent;

    @Autowired
    private SumInfoComponent sumInfoComponent;

    @Autowired
    private UnLockFundComponent unLockFundComponent;

    @Autowired
    private UnLockStockComponent unLockStockComponent;

    @Override
    public void configure() throws Exception {

        /**下单流程异常处理，解冻库存，解冻资产*/
        onException(ApiException.class, Exception.class)
            .handled(false)
            .bean(unLockStockComponent)
            .bean(unLockFundComponent);


        /**订单下单引擎*/
        from("direct:orderEngine")
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
            /**item检查，包含商家，商品库存，item价格变动*/
            .enrich("direct:itemCheck")
            /**营销相关*/
            .enrich("direct:useDiscount")
            /**获取运费，均摊运费*/
            .filter().method(ShippingPredicate.class).bean(shippingComponent).end()
            /**拆单*/
            .bean(splitOrderComponent)
            /**聚合所有信息，包含运费，资源，金额*/
            .bean(sumInfoComponent)
            /**如果是预订单，直接返回，如果是提交订单，执行后续流程*/
            .choice()
                .when(header("operationType").isEqualTo(PreOrder.class.getSimpleName()))
                    .bean(preOrderResultComponent)
                .otherwise().enrich("direct:saveOrder")
            .end();

        /**下单校验，包含itemDetail有效性，收货地址有效性等*/
        from("direct:itemCheck").routingSlip().method(ItemCheckPredicate.class).bean(invalidItemCheckComponent);

        /**营销组件*/
        from("direct:useDiscount").routingSlip().method(UseDiscountPredicate.class);

        /**提交订单后续流程,目前该流程未开启事务，如需开启，需配置spring事务管理器*/
        from("direct:saveOrder")
            .bean(saveOrderComponent)
            .bean(lockFundComponent)
            /**是否操作库存*/
            .filter().method(StockPredicate.class).bean(lockStockComponent).end()
            .bean(afterSubmitOrderComponent)
            .bean(submitOrderResultComponent);
    }
}
