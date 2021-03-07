package com.example.demo.params;

import lombok.Data;

/**
 * 预订单/提交订单入参
 * @author licong
 * @date 2021/3/6 上午12:17
 */
@Data
public class SubmitCartItemInfoDTO extends SubmitItemInfoDTO {
    /**购物车id*/
    private Long cartId;
}
