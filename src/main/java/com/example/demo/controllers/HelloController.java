package com.example.demo.controllers;

import com.example.demo.clients.pre.PreActualOrderClient;
import com.example.demo.clients.submit.SubmitActualOrderClient;
import com.example.demo.params.SubmitItemInfoDTO;
import com.example.demo.params.pre.PreActualOrder;
import com.example.demo.params.submit.SubmitActualOrder;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Produce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author licong
 * @date 2021/2/4 下午7:36
 */
@RestController
public class HelloController {

    @Produce("direct:preActualOrder")
    private PreActualOrderClient preActualOrderClient;

    @Produce("direct:submitActualOrder")
    private SubmitActualOrderClient submitActualOrderClient;

    @GetMapping("/preOrder")
    public Object preOrder() {
        PreActualOrder preActualOrder = new PreActualOrder();
        List<SubmitItemInfoDTO> submitItemInfoDTOS = new ArrayList<>();
        SubmitItemInfoDTO submitItemInfoDTO = new SubmitItemInfoDTO();
        submitItemInfoDTO.setItemCode("23ead980xaa3");
        submitItemInfoDTO.setNum(5);
        submitItemInfoDTO.setActivityId(2000L);
        submitItemInfoDTO.setPriceId(432554L);
        submitItemInfoDTOS.add(submitItemInfoDTO);
        preActualOrder.setSubmitItemInfoDTOList(submitItemInfoDTOS);
        OrderContext orderContext = preActualOrderClient.preActualOrder(preActualOrder);
        return orderContext.getPreOrderResultDTO();
    }

    @GetMapping("/submitOrder")
    public Object submitOrder() {
        SubmitActualOrder submitActualOrder = new SubmitActualOrder();
        List<SubmitItemInfoDTO> submitItemInfoDTOS = new ArrayList<>();
        SubmitItemInfoDTO submitItemInfoDTO = new SubmitItemInfoDTO();
        submitItemInfoDTO.setItemCode("23ead980xaa3");
        submitItemInfoDTO.setNum(5);
        submitItemInfoDTO.setActivityId(2000L);
        submitItemInfoDTO.setPriceId(432554L);
        submitItemInfoDTOS.add(submitItemInfoDTO);
        submitActualOrder.setSubmitItemInfoDTOList(submitItemInfoDTOS);
        OrderContext orderContext = submitActualOrderClient.submitActualOrder(submitActualOrder);
        return orderContext.getSubmitOrderResultDTO();
    }
}
