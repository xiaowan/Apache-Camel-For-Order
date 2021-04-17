package com.example.demo.config.biz;

import lombok.Data;

/**
 * 库存相关配置
 */

@Data
public class StockCheck {

    /**订单类型*/
    private String orderType;

    /**是否校验库存*/
    private Boolean checkStock;

    /**是否扣除库存*/
    private Boolean lockStock;

}
