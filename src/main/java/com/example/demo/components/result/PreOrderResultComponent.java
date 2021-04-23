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
public class PreOrderResultComponent extends AbstractResultOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {

        /**使用资源聚合到商家维度*/
        this.calculateOrderResourceOnMerchant(orderContext.getMerchantItems());


        PreOrderResultDTO preOrderResultDTO = new PreOrderResultDTO();
        preOrderResultDTO.setMerchantItems(orderContext.getMerchantItems());
        preOrderResultDTO.setUseDiscountComponents(orderContext.getDiscountComponent());
        preOrderResultDTO.setItemCheck(orderContext.getItemChecks());
        preOrderResultDTO.setInvalidItemDetailDTOS(orderContext.getInvalidItemDetails());
        preOrderResultDTO.getTotalInfoDTO().setOrderResources(this.calculateOrderResourceOnOrder(orderContext.getMerchantItems()));

        exchange.getIn().setBody(preOrderResultDTO, PreOrderResultDTO.class);

        /**防止内存泄漏*/
        orderContext = null;

    }

}
