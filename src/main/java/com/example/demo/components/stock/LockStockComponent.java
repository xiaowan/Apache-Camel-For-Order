package com.example.demo.components.stock;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 锁库存操作
 * @author licong
 * @date 2021/3/6 下午11:28
 */
@Component
public class LockStockComponent extends AbstractStockOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getSubmitOrderOption().add("预先锁定库存");
    }

}
