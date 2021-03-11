package com.example.demo.components.result;

import com.example.demo.params.internal.SubmitOrderResultDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 提交订单结果聚合
 * @author licong
 * @date 2021/3/6 下午10:48
 */
@Component
public class SubmitOrderResultComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        SubmitOrderResultDTO submitOrderResultDTO = new SubmitOrderResultDTO();
        submitOrderResultDTO.setMerchantItemDTOList(orderContext.getMerchantItemDTOS());
        submitOrderResultDTO.setDiscountComponent(orderContext.getDiscountComponent());
        submitOrderResultDTO.setSubmitOrderOption(orderContext.getSubmitOrderOption());
        submitOrderResultDTO.setInvalidItemDetailDTOS(orderContext.getInvalidItemDetailDTOS());

        exchange.getIn().setBody(submitOrderResultDTO, SubmitOrderResultDTO.class);
    }
}
