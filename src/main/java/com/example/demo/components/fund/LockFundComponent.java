package com.example.demo.components.fund;

import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ApiException;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 保存订单
 * @author licong
 * @date 2021/3/6 下午11:28
 */
@Component
public class LockFundComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        System.out.println("冻结资产中心资产");
        orderContext.getSubmitOrderOption().add("预先锁定资产中心资源");

        if(ThreadLocalRandom.current().nextBoolean()) {
            throw new ApiException(ErrorEnum.SUBMIT_ORDER_ERROR);
        }
    }

}
