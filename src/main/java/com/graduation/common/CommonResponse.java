package com.graduation.common;

import com.graduation.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回值  字段名不能更改  有些是layui要求的返回值字段
 * @param <T>
 */
@Data
public class CommonResponse<T>{
    // 返回状态码 0表示成功
    private Integer code;
    // 返回描述
    private String msg;
    // 返回的数据
    private T data;
    // 分页查询的总记录数
    private Long  count;

    // 私有化构造器
    private CommonResponse(){}

    private CommonResponse(Integer code, String msg, T data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 返回成功
     * @param <T>
     * @return
     */
    public static <T> CommonResponse successInstance(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(StatusCode.OPERATION_SUCCESS.getCode());
        commonResponse.setMsg(StatusCode.OPERATION_SUCCESS.getMsg());
        commonResponse.setData(null);
        commonResponse.setCount(null);
        return commonResponse;
    }

    /**
     * 返回成功 及 成功消息
     * @param <T>
     * @return
     */
    public static <T> CommonResponse successInstance(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(StatusCode.OPERATION_SUCCESS.getCode());
        commonResponse.setMsg(msg);
        commonResponse.setData(null);
        commonResponse.setCount(null);
        return commonResponse;
    }

    /**
     * 返回成功 返回数据及总数
     * @param <T>
     * @return
     */
    public static <T> CommonResponse successInstance(T data,Long count){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(StatusCode.OPERATION_SUCCESS.getCode());
        commonResponse.setMsg(StatusCode.OPERATION_SUCCESS.getMsg());
        commonResponse.setData(data);
        commonResponse.setCount(count);
        return commonResponse;
    }

    /**
     * 返回失败
     * @param <T>
     * @return
     */
    public static <T> CommonResponse errorInstance(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(StatusCode.OPERATION_ERROR.getCode());
        commonResponse.setMsg(StatusCode.OPERATION_ERROR.getMsg());
        commonResponse.setData(null);
        commonResponse.setCount(null);
        return commonResponse;
    }

    /**
     * 返回失败 及 error消息
     * @param <T>
     * @return
     */
    public static <T> CommonResponse errorInstance(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(StatusCode.OPERATION_ERROR.getCode());
        commonResponse.setMsg(msg);
        commonResponse.setData(null);
        commonResponse.setCount(null);
        return commonResponse;
    }

}
