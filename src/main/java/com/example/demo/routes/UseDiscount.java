package com.example.demo.routes;

import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Exchange;
import org.apache.camel.Header;


/**
 * @author licong
 * @date 2021/3/8 下午12:53
 */
public class UseDiscount {

    public String useDiscountMethod(OrderContext orderContext) {
        return "bean:useMemberDiscountComponent";
    }
}
