package com.example.demo.config.biz;

import lombok.Data;

import java.util.List;

/**
 * item 检查组件
 */

@Data
public class ItemCheck {

    /**订单类型*/
    private String orderType;

    /**检查组件*/
    private List<String> itemCheckComponent;

}
