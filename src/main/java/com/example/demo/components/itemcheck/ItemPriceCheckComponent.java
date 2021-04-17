package com.example.demo.components.itemcheck;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 校验商品金额
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("itemPriceCheckComponent")
public class ItemPriceCheckComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getItemChecks().add("订单金额检查");
    }

}
