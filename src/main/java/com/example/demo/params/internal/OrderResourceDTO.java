package com.example.demo.params.internal;

import lombok.Data;

/**
 * 订单使用的资源
 */

@Data
public class OrderResourceDTO {

    /**资源名称*/
    private String resourceType;

    /**资源id*/
    private Long resourceId;

    /**使用资源抵扣的金额*/
    private Integer amount;

}
