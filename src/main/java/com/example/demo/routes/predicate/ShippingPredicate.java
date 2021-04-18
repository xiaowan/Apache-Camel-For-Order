package com.example.demo.routes.predicate;

import com.example.demo.params.CommonOrder;
import com.example.demo.params.pre.PreVirtualOrder;
import com.example.demo.params.submit.SubmitVirtualOrder;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Body;

/**
 * 判断是否需要计算运费
 * @author licong
 * @date 2021/3/12 下午7:58
 */
public class ShippingPredicate {

    public boolean predicate(@Body OrderContext orderContext) {
        /**目前全部计算运费*/
        return Boolean.TRUE;
    }
}
