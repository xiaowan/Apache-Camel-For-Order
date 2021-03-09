package com.example.demo.components.discount;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 使用金币抵扣
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("useGoldDiscountComponent")
public class UseGoldDiscountComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getDiscountComponent().add("使用金币抵扣");
    }

}
