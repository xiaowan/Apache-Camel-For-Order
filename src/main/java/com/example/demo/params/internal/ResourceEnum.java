package com.example.demo.params.internal;

import lombok.Data;

/**
 * 资源枚举
 */
public enum ResourceEnum {
    COUPON("COUPON", "优惠券", true),
    MEMBER("MEMBER", "会员优惠", true),
    FUND("FUND", "资产", true),
    GOLD("GOLD", "金币", true),
    ;

    /**资源类型*/
    private String resourceType;

    /**描述*/
    private String desc;

    /**是否是金额优惠*/
    private boolean discountFlag;

    ResourceEnum(String resourceType, String desc, boolean discountFlag) {
        this.resourceType = resourceType;
        this.desc = desc;
        this.discountFlag = discountFlag;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getDiscountFlag() {
        return discountFlag;
    }
}
