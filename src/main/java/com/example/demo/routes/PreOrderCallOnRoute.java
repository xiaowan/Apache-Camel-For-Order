package com.example.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 预订单
 * @author licong
 * @date 2021/2/4 下午7:38
 */
@Component
public class PreOrderCallOnRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /**下单入口*/
        from("direct:preVirtualOrder").to("direct:aggregationInputOrderParam");

        from("direct:preActualOrder").to("direct:aggregationInputOrderParam");

        from("direct:preCartOrder").to("direct:aggregationInputOrderParam");

    }
}
