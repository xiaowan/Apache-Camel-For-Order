package com.example.demo.components.order;


import com.example.demo.params.CommonOrder;
import com.example.demo.params.SubmitItemInfoDTO;
import com.example.demo.params.internal.InternalItemInfo;
import com.example.demo.params.pre.PreOrder;
import com.example.demo.params.submit.SubmitOrder;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 预订单/提交订单入参转换
 * @author licong
 * @date 2021/3/5 下午11:38
 */
@Component
public class OrderInputParamConvertComponent {

    /**
     * 普通提交订单
     * @param commonOrder
     */
    public void generateSubmitItemInfo(@Body CommonOrder commonOrder, Exchange exchange) {
        List<InternalItemInfo> internalItemInfos = new ArrayList<>();
        if (commonOrder != null && !CollectionUtils.isEmpty(commonOrder.getSubmitItemInfoDTOList())) {
            for (SubmitItemInfoDTO submitItemInfoDTO : commonOrder.getSubmitItemInfoDTOList()) {
                InternalItemInfo internalItemInfo = new InternalItemInfo();
                internalItemInfo.setItemCode(submitItemInfoDTO.getItemCode());
                internalItemInfo.setNum(submitItemInfoDTO.getNum());
                internalItemInfo.setActivityId(submitItemInfoDTO.getActivityId());
                internalItemInfo.setPriceId(submitItemInfoDTO.getPriceId());
                internalItemInfos.add(internalItemInfo);
            }
        }

        OrderContext orderContext = new OrderContext();
        orderContext.setCommonOrder(commonOrder);
        orderContext.setInternalItemInfoList(internalItemInfos);
        exchange.getIn().setBody(orderContext, OrderContext.class);

        this.fillExchangeHeader(commonOrder, exchange);
    }

    public void generateSubmitCartItemInfo(@Body CommonOrder commonOrder, Exchange exchange) {
        System.out.println(this.getClass().getName());
    }

    /**
     * 填充exchange头部信息
     * @param commonOrder
     * @param exchange
     */
    private void fillExchangeHeader(CommonOrder commonOrder, Exchange exchange) {
        Message message = exchange.getIn();
        String className = null;
        if (commonOrder instanceof PreOrder) {
            className = PreOrder.class.getSimpleName();
        } else {
            className = SubmitOrder.class.getSimpleName();
        }
        message.setHeader("operationType", className);
    }
}
