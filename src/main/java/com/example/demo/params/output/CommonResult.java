package com.example.demo.params.output;

import lombok.Data;

/**
 * @author licong
 * @date 2021/3/7 下午10:44
 */
@Data
public class CommonResult<T> {
    private Integer code = 0 ;

    private String msg;

    private T data;
}
