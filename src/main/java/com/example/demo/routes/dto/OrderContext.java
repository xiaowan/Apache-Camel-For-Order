package com.example.demo.routes.dto;

import com.example.demo.input.CommonOrder;
import com.example.demo.input.internal.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author licong
 * @date 2021/3/5 上午9:39
 */
@Data
public class  OrderContext {

    /**下单入参*/
    private CommonOrder commonOrder;

    /**转换后的下单入参*/
    private List<InternalItemInfo> internalItemInfoList;

    /**商品详情*/
    private List<ItemDetailDTO> itemDetailDTOS;

    /**商家维度商品聚合*/
    private List<MerchantItemDTO> merchantItemDTOS;

    /**无效商品列表*/
    private List<ItemDetailDTO> invalidItemDetailDTOS;

    /**优惠使用情况*/
    private List<String> discountComponent = new ArrayList<>();

    /**提交订单后续动作*/
    private List<String> submitOrderOption = new ArrayList<>();

    /**预订单结果返回*/
    private PreOrderResultDTO preOrderResultDTO;

    /**提交订单结果返回*/
    private SubmitOrderResultDTO submitOrderResultDTO;

}
