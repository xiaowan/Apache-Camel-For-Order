package com.example.demo.routes.dto;

import com.example.demo.config.biz.OrderConfiguration;
import com.example.demo.params.CommonOrder;
import com.example.demo.params.internal.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author licong
 * @date 2021/3/5 上午9:39
 */
@Data
public class  OrderContext {

    /**对应业务线的订单配置*/
    private OrderConfiguration orderConfiguration;

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

    /**item检查*/
    private List<String> itemChecks = new ArrayList<>();

    /**提交订单后续动作*/
    private List<String> submitOrderOption = new ArrayList<>();

    /**订单维度金额，资源聚合*/
    private TotalInfoDTO totalInfoDTO = new TotalInfoDTO();


}
