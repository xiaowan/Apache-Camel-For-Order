package com.example.demo.exception;

import com.example.demo.enums.ErrorEnum;
import lombok.Data;

/**
 * @author licong
 * @date 2021/3/7 下午10:49
 */
@Data
public class ApiException extends RuntimeException {
    private Integer code;
    private String msg;

    public ApiException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
    }
}
