package com.example.demo.clients.pre;

import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.params.pre.PreVirtualOrder;

/**
 * @author licong
 * @date 2021/3/5 上午9:58
 */
public interface PreVirtualOrderClient {
    PreOrderResultDTO preVirtualOrder(PreVirtualOrder preVirtualOrder);
}
