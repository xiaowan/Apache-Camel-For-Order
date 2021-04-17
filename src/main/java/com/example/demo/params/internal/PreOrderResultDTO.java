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
    private List<MerchantItemDTO> merchantItemDTOList;

    private List<String> useDiscountComponents;

    private List<String> itemCheck;

    /**无效商品列表*/
    private List<ItemDetailDTO> invalidItemDetailDTOS;
}
