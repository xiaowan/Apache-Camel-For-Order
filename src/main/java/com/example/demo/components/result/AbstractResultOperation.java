package com.example.demo.components.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.params.internal.OrderResourceDTO;
import org.springframework.util.CollectionUtils;

/**
 * 统一返回操作
 */
public abstract class AbstractResultOperation {

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
                newOrderResource.setResourceType(tmpOrderResource.getResourceType());
                newOrderResource.setResourceId(tmpOrderResource.getResourceId());
                newOrderResource.setAmount(tmpOrderResource.getAmount());
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
