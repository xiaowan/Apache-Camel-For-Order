package com.example.demo.clients.pre;

import com.example.demo.routes.dto.OrderContext;

/**
 * @author licong
 * @date 2021/3/5 上午9:58
 */
public interface PreVirtualOrderClient {
    OrderContext preVirtualOrder(OrderContext orderContext);
}
