package com.example.demo.config.biz;

import lombok.Data;

/**
 * 购物车相关配置
 */
@Data
public class CartLimit {

    /**单一item购买数量限制*/
    private Integer singleItemLimit;

    /**item种类限制*/
    private Integer cartItemLimit;

    /**店铺购物车数量限制*/
    private Integer shopItemLimit;

}
