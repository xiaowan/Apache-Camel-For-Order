package com.example.demo.clients.pre;

import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.params.pre.PreCartOrder;

/**
 * @author licong
 * @date 2021/3/5 上午9:58
 */
public interface PreCartOrderClient {
    PreOrderResultDTO preCartOrder(PreCartOrder preCartOrder);
}
