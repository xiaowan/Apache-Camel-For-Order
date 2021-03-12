package com.example.demo.components.shipping;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 运费组件（获取运费，均摊运费）
 * @author licong
 * @date 2021/3/12 下午2:40
 */
@Component
public class ShippingComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {

    }
}
