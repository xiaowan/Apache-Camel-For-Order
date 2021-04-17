package com.example.demo.config.biz;

import lombok.Data;

import java.util.List;

/**
 * 订单相关配置
 */
@Data
public class OrderConfiguration {

    /**购物车相关*/
    private CartLimit cartLimit;

    /**延迟取消订单时间配置*/
    private CancelDelay cancelDelay;

    /**订单金额限制*/
    private List<OrderAmount> orderAmount;

    /**是否校验库存，是否扣库存*/
    private List<StockCheck> stockCheck;

    /**自动确认收货时间*/
    private List<AutoConfirmDelay> autoConfirmDelay;

    /**可用资产抵扣*/
    private List<Discount> discounts;

    /**item检查组件*/
    private List<ItemCheck> itemChecks;

}
