package com.example.demo.config.biz;

import lombok.Data;

import java.util.List;

/**
 * 营销组件
 */

@Data
public class Discount {

    /**订单类型*/
    private String orderType;

    /**营销组件*/
    private List<String> discountComponent;

}
