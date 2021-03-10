rebuild order-center using apache camel
```java
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
    /**调用商家中心，获取店铺详情*/
    .bean(shopComponent)
    .enrich("direct:checkAvailability")
    //.bean("按照商家维度聚合itemDetail")
    /**营销流程*/
    .enrich("direct:useDiscount")
    //.bean("获取运费，均摊运费")
    //.bean("拆单")
    /**如果是预订单，直接返回，如果是提交订单，执行后续流程*/
    .choice()
        .when(header("operationType").isEqualTo(PreOrder.class.getSimpleName()))
            .bean(preOrderResultComponent)
        .otherwise().enrich("direct:saveOrder")
    .end();
```