package com.example.demo.config.biz;

import lombok.Data;

/**
 * 止血开关
 */

@Data
public class Stanch {

    /**跳过风控*/
    private Boolean skipRickControl;

    /**获取商家组信息*/
    private Boolean skipGetShopGroupInfo;

}
