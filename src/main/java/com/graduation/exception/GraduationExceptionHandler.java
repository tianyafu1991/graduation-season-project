package com.graduation.exception;

import com.graduation.common.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GraduationExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResponse<Object> handleAllException(Exception e){
        return CommonResponse.errorInstance("系统错误:" + e.getMessage());
    }
}
