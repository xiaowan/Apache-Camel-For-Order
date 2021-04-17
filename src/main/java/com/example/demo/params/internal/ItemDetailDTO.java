package com.example.demo.params.internal;

import lombok.Data;

/**
 * 商品详情
 * @author licong
 * @date 2021/3/6 下午9:09
 */
@Data
public class ItemDetailDTO {

    /**商品code*/
    private String itemCode;

    /**购买数量*/
    private Integer num;

    /**缩略图*/
    private String thumbImg;

    /**商品名称*/
    private String itemName;

    /**价格id*/
    private Long priceId;

    /**活动id*/
    private Long activityId;

    /**商家id*/
    private Long merchantId;

    /**店铺id*/
    private Long shopId;

    /**商品是否无效*/
    private Boolean invalid;

    /**库存数量*/
    private Integer stock;

}
