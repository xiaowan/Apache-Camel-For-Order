package com.example.demo.input.internal;

import lombok.Data;

/**
 * 商品详情
 * @author licong
 * @date 2021/3/6 下午9:09
 */
@Data
public class ItemDetailDTO {
    private String itemCode;

    private Integer num;

    private String thumbImg;

    private String itemName;

    private Long priceId;

    private Long activityId;

    private Long merchantId;

    private Long shopId;

    /**商品是否无效*/
    private Boolean invalid;

}
