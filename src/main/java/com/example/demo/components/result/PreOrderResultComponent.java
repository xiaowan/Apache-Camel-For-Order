package com.example.demo.components.result;

import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 预订单结果聚合
 * @author licong
 * @date 2021/3/6 下午10:48
 */
@Component
public class PreOrderResultComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        PreOrderResultDTO preOrderResultDTO = new PreOrderResultDTO();
        preOrderResultDTO.setMerchantItemDTOList(orderContext.getMerchantItemDTOS());
        preOrderResultDTO.setUseDiscountComponents(orderContext.getDiscountComponent());
        preOrderResultDTO.setInvalidItemDetailDTOS(orderContext.getInvalidItemDetailDTOS());
        orderContext.setPreOrderResultDTO(preOrderResultDTO);

    }
}
