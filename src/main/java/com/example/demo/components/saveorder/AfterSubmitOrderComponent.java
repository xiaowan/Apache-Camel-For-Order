package com.example.demo.components.saveorder;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 保存订单
 * @author licong
 * @date 2021/3/6 下午11:28
 */
@Component
public class AfterSubmitOrderComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getSubmitOrderOption().add("保存订单，记录下单日志，发送下单事件");
    }
}
