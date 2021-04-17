package com.example.demo.config.biz;

import lombok.Data;

/**
 * 订单金额限制
 */

@Data
public class OrderAmount {

    /**订单类型*/
    private String orderType;

    /**订单金额*/
    private Integer orderAmount = 0;

}
