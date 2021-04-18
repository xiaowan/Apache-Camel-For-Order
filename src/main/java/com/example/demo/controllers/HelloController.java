package com.example.demo.controllers;

import com.example.demo.clients.pre.PreActualOrderClient;
import com.example.demo.clients.pre.PreVirtualOrderClient;
import com.example.demo.clients.submit.SubmitActualOrderClient;
import com.example.demo.params.SubmitItemInfoDTO;
import com.example.demo.params.internal.PreOrderResultDTO;
import com.example.demo.params.internal.SubmitOrderResultDTO;
import com.example.demo.params.pre.PreActualOrder;
import com.example.demo.params.pre.PreVirtualOrder;
import com.example.demo.params.submit.SubmitActualOrder;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Produce;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Produce("direct:preVirtualOrder")
    private PreVirtualOrderClient preVirtualOrderClient;

    @Produce("direct:submitActualOrder")
    private SubmitActualOrderClient submitActualOrderClient;

    @GetMapping("/preVirtualOrder")
    public PreOrderResultDTO preVirtualOrder() {
        PreVirtualOrder preVirtualOrder = new PreVirtualOrder();
        preVirtualOrder.setOrderType("COMMON");
        preVirtualOrder.setBizId("C_TERMINAL");
        List<SubmitItemInfoDTO> submitItemInfoDTOS = new ArrayList<>();
        SubmitItemInfoDTO submitItemInfoDTO = new SubmitItemInfoDTO();
        submitItemInfoDTO.setItemCode("23ead980xaa3");
        submitItemInfoDTO.setNum(5);
        submitItemInfoDTO.setActivityId(2000L);
        submitItemInfoDTO.setPriceId(432554L);
        submitItemInfoDTOS.add(submitItemInfoDTO);
        preVirtualOrder.setSubmitItemInfoDTOList(submitItemInfoDTOS);
        return preVirtualOrderClient.preVirtualOrder(preVirtualOrder);
    }

    @GetMapping("/preOrder")
    public PreOrderResultDTO preOrder() {
        PreActualOrder preActualOrder = new PreActualOrder();
        List<SubmitItemInfoDTO> submitItemInfoDTOS = new ArrayList<>();
        SubmitItemInfoDTO submitItemInfoDTO = new SubmitItemInfoDTO();
        submitItemInfoDTO.setItemCode("23ead980xaa3");
        submitItemInfoDTO.setNum(5);
        submitItemInfoDTO.setActivityId(2000L);
        submitItemInfoDTO.setPriceId(432554L);
        submitItemInfoDTOS.add(submitItemInfoDTO);
        preActualOrder.setSubmitItemInfoDTOList(submitItemInfoDTOS);
        return preActualOrderClient.preActualOrder(preActualOrder);
    }

    @GetMapping("/submitOrder")
    public SubmitOrderResultDTO submitOrder() {
        SubmitActualOrder submitActualOrder = new SubmitActualOrder();
        submitActualOrder.setOrderType("COMMON");
        submitActualOrder.setBizId("C_TERMINAL");
        List<SubmitItemInfoDTO> submitItemInfoDTOS = new ArrayList<>();
        SubmitItemInfoDTO submitItemInfoDTO = new SubmitItemInfoDTO();
        submitItemInfoDTO.setItemCode("23ead980xaa3");
        submitItemInfoDTO.setNum(5);
        submitItemInfoDTO.setActivityId(2000L);
        submitItemInfoDTO.setPriceId(432554L);
        submitItemInfoDTOS.add(submitItemInfoDTO);
        submitActualOrder.setSubmitItemInfoDTOList(submitItemInfoDTOS);
        return submitActualOrderClient.submitActualOrder(submitActualOrder);
    }
}
