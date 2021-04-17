package com.example.demo.config.biz;

import lombok.Data;

/**
 * 自动确认收货配置
 */

@Data
public class AutoConfirmDelay {

    /**业务线*/
    private String orderType;

    /**自动确认收货时间*/
    private Integer confirmSeconds;

}
