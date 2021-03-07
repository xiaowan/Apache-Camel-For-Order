package com.example.demo.input;

import lombok.Data;

/**
 * 预订单/提交订单入参
 * @author licong
 * @date 2021/3/6 上午12:17
 */
@Data
public class SubmitItemInfoDTO {
    /**商品code*/
    private String itemCode;

    /**购买数量*/
    private Integer num;

    /**活动id*/
    private Long activityId;

    /**价格id*/
    private Long priceId;
}
