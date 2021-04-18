package com.example.demo.components.discount;

import com.example.demo.params.internal.ItemDetailDTO;

import com.example.demo.params.internal.MerchantItemDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * 营销相关操作
 */
public abstract class AbstractDiscountOperation {

    /**
     * 获取所有店铺下的有效item
     * @param merchantItemDTOS
     * @return
     */
    public List<ItemDetailDTO> getValidItems(List<MerchantItemDTO> merchantItemDTOS) {
        List<ItemDetailDTO> itemDetailDTOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return itemDetailDTOS;
        }

        merchantItemDTOS.stream()
            .filter(merchantItem -> !CollectionUtils.isEmpty(merchantItem.getItemDetails()))
            .map(merchantItem -> itemDetailDTOS.addAll(merchantItem.getItemDetails()))
            .collect(Collectors.toList());

        return itemDetailDTOS;

    }
}
