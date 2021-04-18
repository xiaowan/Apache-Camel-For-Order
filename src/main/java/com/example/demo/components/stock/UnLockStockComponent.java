package com.example.demo.components.stock;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 释放库存
 * @author licong
 * @date 2021/3/6 下午11:28
 */
@Component
public class UnLockStockComponent extends AbstractStockOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        System.out.println("解锁库存");
        orderContext.getSubmitOrderOption().add("预先锁定库存");
    }
}
