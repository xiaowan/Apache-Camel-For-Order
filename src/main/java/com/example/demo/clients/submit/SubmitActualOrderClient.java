package com.example.demo.clients.submit;

import com.example.demo.params.internal.SubmitOrderResultDTO;
import com.example.demo.params.submit.SubmitActualOrder;

/**
 * @author licong
 * @date 2021/3/5 上午9:58
 */
public interface SubmitActualOrderClient {
    SubmitOrderResultDTO submitActualOrder(SubmitActualOrder submitActualOrder);
}
