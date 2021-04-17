package com.example.demo.config.biz;

import lombok.Data;

/**
 * 订单延迟取消时间配置
 */

@Data
public class CancelDelay {

    /**倒计时取消秒数*/
    private Integer cancelSeconds;

    /**倒计时提醒*/
    private Integer aboutToCancel;

}
