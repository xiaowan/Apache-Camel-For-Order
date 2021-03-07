package com.example.demo.params;

import lombok.Data;

import java.util.List;

/**
 * @author licong
 * @date 2021/3/4 下午11:46
 */
@Data
public class CommonOrder {

    /**提交订单item入参*/
    private List<SubmitItemInfoDTO> submitItemInfoDTOList;
}
