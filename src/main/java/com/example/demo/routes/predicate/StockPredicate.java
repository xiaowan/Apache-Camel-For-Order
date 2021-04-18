package com.example.demo.routes.predicate;

import com.example.demo.config.biz.OrderConfiguration;
import com.example.demo.config.biz.StockCheck;
import com.example.demo.routes.dto.OrderContext;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.camel.Body;
import org.springframework.util.CollectionUtils;

/**
 * 判断是否需要操作库存
 * @author licong
 * @date 2021/3/12 下午7:58
 */
public class StockPredicate {

    public boolean predicate(@Body OrderContext orderContext) {
        String orderType = orderContext.getCommonOrder().getOrderType();
        OrderConfiguration orderConfiguration = orderContext.getOrderConfiguration();
        StockCheck stockCheck = orderConfiguration.getStockCheckConfig(orderType);
        if (stockCheck != null) {
            return stockCheck.getLockStock();
        }

        return false;
    }
}
