package com.example.demo.routes.predicate;

import com.example.demo.config.biz.OrderConfiguration;
import com.example.demo.routes.dto.OrderContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * @author licong
 * @date 2021/3/8 下午12:53
 */
public class UseDiscountPredicate {

    public String useDiscount(OrderContext orderContext) {
        String orderType = orderContext.getCommonOrder().getOrderType();
        OrderConfiguration orderConfiguration = orderContext.getOrderConfiguration();
        List<String> discountComponents = orderConfiguration.getDiscountComponent(orderType);
        if (CollectionUtils.isEmpty(discountComponents)) {
            return null;
        }
        return discountComponents.stream().collect(Collectors.joining(","));
    }
}
