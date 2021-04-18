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

    /**原始售价*/
    private Integer origPrice;

    /**实际售价*/
    private Integer realPrice;

    /**所有的优惠金额*/
    private Integer discountAmount = 0;

    /**运费*/
    private Integer shippingAmount = 0;

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

    public Integer sumItemRealPrice() {
        return this.num * this.realPrice;
    }

}
