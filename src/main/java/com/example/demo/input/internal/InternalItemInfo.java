package com.example.demo.input.internal;

import lombok.Data;

/**
 * 预订单/下单内部入口对象，负责聚合外部提交过来的参数
 * @author licong
 * @date 2021/3/6 下午8:37
 */
@Data
public class InternalItemInfo {
    /**提交的商品code*/
    private String itemCode;

    /**购买的商品数量*/
    private Integer num;

    /**商品价格ID*/
    private Long priceId;

    /**参与活动ID*/
    private Long activityId;
}
