package com.example.demo.params.internal;

import lombok.Data;
import java.util.List;

/**
 * 预订单结果返回
 * @author licong
 * @date 2021/3/6 下午10:49
 */
@Data
public class PreOrderResultDTO {

    /**所有商家信息*/
    private List<MerchantItemDTO> merchantItems;

    private List<String> useDiscountComponents;

    private List<String> itemCheck;

    /**无效商品列表*/
    private List<ItemDetailDTO> invalidItemDetailDTOS;

    /**订单纬度商品金额详情*/
    private TotalInfoDTO totalInfoDTO = new TotalInfoDTO();

}
