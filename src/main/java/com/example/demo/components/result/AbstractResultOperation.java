package com.example.demo.components.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.MerchantItemDTO;
import com.example.demo.params.internal.OrderResourceDTO;
import org.springframework.util.CollectionUtils;

/**
 * 统一返回操作
 */
public abstract class AbstractResultOperation {

    /**
     * 使用资源聚合到订单维度
     * @param merchantItemDTOS
     */
    public List<OrderResourceDTO> calculateOrderResourceOnOrder(List<MerchantItemDTO> merchantItemDTOS) {
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            new ArrayList<>();
        }

        List<OrderResourceDTO> orderResourceDTOS = new ArrayList<>();
        for (MerchantItemDTO merchantItemDTO : merchantItemDTOS) {
            if (CollectionUtils.isEmpty(merchantItemDTO.getItemDetails())) {
                continue;
            }
            for (ItemDetailDTO itemDetailDTO : merchantItemDTO.getItemDetails()) {
                if (CollectionUtils.isEmpty(itemDetailDTO.getOrderResources())) {
                    continue;
                }
                orderResourceDTOS.addAll(itemDetailDTO.getOrderResources());
            }
        }

        return this.calculateOrderResource(orderResourceDTOS);
    }

    /**
     * 使用资源聚合到商家维度
     */
    public void calculateOrderResourceOnMerchant(List<MerchantItemDTO> merchantItemDTOS) {
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return;
        }

        for (MerchantItemDTO merchantItemDTO : merchantItemDTOS) {
            if (CollectionUtils.isEmpty(merchantItemDTO.getItemDetails())) {
                continue;
            }
            List<OrderResourceDTO> orderResourceDTOS = new ArrayList<>();
            for (ItemDetailDTO itemDetailDTO : merchantItemDTO.getItemDetails()) {
                if (CollectionUtils.isEmpty(itemDetailDTO.getOrderResources())) {
                    continue;
                }
                orderResourceDTOS.addAll(itemDetailDTO.getOrderResources());
            }

            if (CollectionUtils.isEmpty(orderResourceDTOS)) {
                continue;
            }
            merchantItemDTO.getTotalInfoDTO().setOrderResources(this.calculateOrderResource(orderResourceDTOS));
        }

    }

    /**
     * 聚合商品纬度的资产
     */
    protected List<OrderResourceDTO> calculateOrderResource(List<OrderResourceDTO> orderResourceDTOS) {
        if (CollectionUtils.isEmpty(orderResourceDTOS)) {
            return new ArrayList<>();
        }

        LinkedHashMap<String, OrderResourceDTO> orderResourceDTOLinkedHashMap = new LinkedHashMap<>();
        for (OrderResourceDTO orderResourceDTO : orderResourceDTOS) {
            OrderResourceDTO tmpOrderResource = orderResourceDTOLinkedHashMap.get(this.getKey(orderResourceDTO));
            if (tmpOrderResource == null) {
                OrderResourceDTO newOrderResource = new OrderResourceDTO();
                newOrderResource.setResourceType(orderResourceDTO.getResourceType());
                newOrderResource.setResourceId(orderResourceDTO.getResourceId());
                newOrderResource.setAmount(orderResourceDTO.getAmount());
                orderResourceDTOLinkedHashMap.put(this.getKey(orderResourceDTO), newOrderResource);
            } else {
                tmpOrderResource.setAmount(tmpOrderResource.getAmount() + orderResourceDTO.getAmount());
            }
        }

        return new ArrayList<>(orderResourceDTOLinkedHashMap.values());
    }

    private String getKey(OrderResourceDTO orderResourceDTO) {
        return orderResourceDTO.getResourceType() + "|" + orderResourceDTO.getResourceId();
    }

}
