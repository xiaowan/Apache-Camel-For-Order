package com.example.demo.params;

import lombok.Data;

import java.util.List;

/**
 * @author licong
 * @date 2021/3/4 下午11:46
 */
@Data
public class CommonOrder {

    /**业务线*/
    private String bizId;

    /**订单类型*/
    private String orderType;

    /**提交订单item入参*/
    private List<SubmitItemInfoDTO> submitItemInfoDTOList;
}
