package com.example.demo.components.fund;

import com.example.demo.exception.ApiException;
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
public class UnLockFundComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        /**该组件内代码需自行保证不允许抛出异常*/
        System.out.println("解锁资源");
    }

}
