package com.example.demo.controllers;

import com.example.demo.exception.ApiException;
import com.example.demo.params.output.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author licong
 * @date 2021/3/7 下午10:48
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public CommonResult handleApiException(HttpServletRequest request, ApiException apiException) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(apiException.getCode());
        commonResult.setMsg(apiException.getMsg());
        return commonResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult handleApiException(HttpServletRequest request, Exception exception) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(500);
        commonResult.setMsg(exception.getMessage());
        return commonResult;
    }

}
