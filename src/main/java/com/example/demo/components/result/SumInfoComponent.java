package com.example.demo.components.result;

import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 订单各项数据聚合，包含运费，订单金额，优惠金额，使用资源
 * @author licong
 * @date 2021/3/6 下午10:48
 */
@Component
public class SumInfoComponent extends AbstractResultOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {

        /**使用资源聚合到商家维度*/
        this.calculateOrderResourceOnMerchant(orderContext.getMerchantItems());

        /**使用资源情况聚合到订单维度*/
        orderContext.getTotalInfoDTO().setOrderResources(this.calculateOrderResourceOnOrder(orderContext.getMerchantItems()));

    }

}
