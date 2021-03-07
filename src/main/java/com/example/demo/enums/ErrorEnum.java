package com.example.demo.enums;

/**
 * @author licong
 * @date 2021/3/7 下午10:50
 */
public enum ErrorEnum {
    PARAM_ERROR(1001, "参数错误"),
    ;

    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
