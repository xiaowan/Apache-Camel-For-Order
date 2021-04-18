rebuild order-center using apache camel
```java
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
        /**item检查，包含商家，商品库存，item价格变动*/
        .enrich("direct:itemCheck")
        /**营销相关*/
        .enrich("direct:useDiscount")
        /**获取运费，均摊运费*/
        .filter().method(ShippingPredicate.class).bean(shippingComponent).end()
        /**拆单*/
        .bean(splitOrderComponent)
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
        .bean(lockFundComponent)
        /**是否操作库存*/
        .filter().method(StockPredicate.class).bean(lockStockComponent).end()
        .bean(afterSubmitOrderComponent)
        .bean(submitOrderResultComponent);
}
```
