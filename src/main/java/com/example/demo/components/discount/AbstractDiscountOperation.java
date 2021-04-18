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

    public List<ItemDetailDTO> getValidItems(List<MerchantItemDTO> merchantItemDTOS) {
        List<ItemDetailDTO> itemDetailDTOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return itemDetailDTOS;
        }

        merchantItemDTOS.stream()
            .filter(merchantItem -> !CollectionUtils.isEmpty(merchantItem.getItemDetailDTOList()))
            .map(merchantItem -> itemDetailDTOS.addAll(merchantItem.getItemDetailDTOList()))
            .collect(Collectors.toList());

        return itemDetailDTOS;

    }
}
