package com.example.demo.input.internal;

import lombok.Data;

import java.util.List;

/**
 * 商家组级别隔离
 * @author licong
 * @date 2021/3/6 下午9:19
 */
@Data
public class MerchantItemDTO {
    /**商家ID*/
    private Long merchantId;

    /**店铺ID*/
    private Long shopId;

    /**店铺名称*/
    private String shopName;

    /**店铺图标*/
    private String shopIcon;

    /**商品详情列表*/
    private List<ItemDetailDTO> itemDetailDTOList;
}
