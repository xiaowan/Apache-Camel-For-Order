package com.example.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 提交订单
 * @author licong
 * @date 2021/2/4 下午7:38
 */
@Component
public class SubmitOrderCallOnRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /**下单入口*/
        from("direct:submitVirtualOrder").to("direct:orderEngine");

        from("direct:submitActualOrder").to("direct:orderEngine");

        from("direct:submitCartOrder").to("direct:orderEngine");



    }
}
