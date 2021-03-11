package com.example.demo.clients.pre;

import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.params.pre.PreActualOrder;

/**
 * @author licong
 * @date 2021/3/5 上午9:58
 */
public interface PreActualOrderClient {
    PreOrderResultDTO preActualOrder(PreActualOrder preActualOrder);
}
