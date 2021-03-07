package com.example.demo.components.discount;

import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * 优惠券计算
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component
public class UseCouponComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getDiscountComponent().add("使用优惠券优惠");
    }

}
