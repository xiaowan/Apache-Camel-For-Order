package com.example.demo.components.order;

import com.example.demo.input.internal.ItemDetailDTO;
import com.example.demo.input.internal.MerchantItemDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺相关组件
 * @author licong
 * @date 2021/3/6 下午9:21
 */
@Component
public class ShopComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        List<ItemDetailDTO> itemDetailDTOS = orderContext.getItemDetailDTOS();
        if (CollectionUtils.isEmpty(itemDetailDTOS)) {
            return;
        }

        Map<Long, MerchantItemDTO> merchantItemDTOMap = new LinkedHashMap<>();
        for (ItemDetailDTO itemDetailDTO : itemDetailDTOS) {
            MerchantItemDTO merchantItemDTO = merchantItemDTOMap.getOrDefault(itemDetailDTO.getShopId(), null);
            if (merchantItemDTO == null) {
                merchantItemDTO = this.buildMerchantItemDTO(itemDetailDTO);
                merchantItemDTOMap.put(itemDetailDTO.getShopId(), merchantItemDTO);
            }
            merchantItemDTO.getItemDetailDTOList().add(itemDetailDTO);
        }

        orderContext.setMerchantItemDTOS(new ArrayList<>(merchantItemDTOMap.values()));
        /**将未分组的itemDetail清空*/
        orderContext.setItemDetailDTOS(null);
    }

    /**
     * 构建商家信息
     * @param itemDetailDTO
     * @return
     */
    private MerchantItemDTO buildMerchantItemDTO(ItemDetailDTO itemDetailDTO) {
        MerchantItemDTO merchantItemDTO = new MerchantItemDTO();
        merchantItemDTO.setMerchantId(itemDetailDTO.getMerchantId());
        merchantItemDTO.setShopId(itemDetailDTO.getShopId());
        merchantItemDTO.setItemDetailDTOList(new ArrayList<>());
        merchantItemDTO.setShopName("店铺名称");
        merchantItemDTO.setShopIcon("https://camel.apache.org/_/img/logo-d.svg");
        return merchantItemDTO;
    }

}
