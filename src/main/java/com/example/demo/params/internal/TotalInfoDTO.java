package com.example.demo.params.internal;

import lombok.Data;

/**
 * 金额相关汇总
 */

@Data
public class TotalInfoDTO {

    /**总优惠金额*/
    private Integer discountAmount = 0;

    /**运费金额*/
    private Integer shippingAmount = 0;

    /**订单金额*/
    private Integer orderAmount = 0;

    /**总商品金额*/
    private Integer totalItemRealPrice = 0;

    /**
     * 计算订单金额
     * 实际售价 + 运费 - 优惠
     */
    public void calculateOrderAmount() {
        this.orderAmount = this.totalItemRealPrice + this.shippingAmount - this.discountAmount;
    }

}
