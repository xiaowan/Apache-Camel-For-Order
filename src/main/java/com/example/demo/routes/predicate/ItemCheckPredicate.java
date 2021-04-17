package com.example.demo.routes.predicate;

import com.example.demo.config.biz.OrderConfiguration;
import com.example.demo.routes.dto.OrderContext;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.camel.Body;
import org.springframework.util.CollectionUtils;

/**
 * @author licong
 * @date 2021/3/8 下午12:53
 */
public class ItemCheckPredicate {

    public String itemCheck(@Body OrderContext orderContext) {
        String orderType = orderContext.getCommonOrder().getOrderType();
        OrderConfiguration orderConfiguration = orderContext.getOrderConfiguration();
        List<String> itemcheckComponents = orderConfiguration.getItemCheck(orderType);
        if (CollectionUtils.isEmpty(itemcheckComponents)) {
            return null;
        }
        return itemcheckComponents.stream().collect(Collectors.joining(","));
    }
}
