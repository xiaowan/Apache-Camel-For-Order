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

    /**
     * 获取指定订单类型的item检查组件
     * @param orderType
     * @return
     */
    public List<String> getItemCheck(String orderType) {
        if (itemChecks == null || itemChecks.size() == 0) {
            return null;
        }
        for (ItemCheck itemCheck : itemChecks) {
            if (itemCheck.getOrderType().equals(orderType)) {
                return itemCheck.getItemCheckComponent();
            }
        }
        return null;
    }

    /**
     * 获取可用抵扣组件
     */
    public List<String> getDiscountComponent(String orderType) {
        if (discounts == null || discounts.size() == 0) {
            return null;
        }
        for (Discount discount : discounts) {
            if (discount.getOrderType().equals(orderType)) {
                return discount.getDiscountComponent();
            }
        }
        return null;
    }

    /**
     * 指定订单类型的库存配置
     */
    public StockCheck getStockCheckConfig(String orderType) {
        if (stockCheck == null || stockCheck.size() == 0) {
            return null;
        }
        for (StockCheck tmpStockCheck : stockCheck) {
            if (orderType.equals(tmpStockCheck.getOrderType())) {
                return tmpStockCheck;
            }
        }

        return null;
    }


}
