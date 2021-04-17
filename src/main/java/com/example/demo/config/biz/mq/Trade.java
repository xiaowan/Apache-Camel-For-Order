package com.example.demo.config.biz.mq;

import lombok.Data;

/**
 * 交易系统mq配置
 */

@Data
public class Trade {

    /**支付成功回调*/
    private String paidTag;

    /**退货成功回调*/
    private String refundedTag;

}
