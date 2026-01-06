package com.domye.demo.common;

import com.domye.demo.exception.ErrorCode;

/**
 * 结果工具类
 */
public class ResultUtils {

    /**
     * 成功响应
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 成功响应，带自定义消息
     * @param data 数据
     * @param msg 自定义消息
     * @param <T>  数据类型
     * @return 响应
     */
    public static <T> BaseResponse<T> success(T data, String msg) {
        return new BaseResponse<>(0, data, msg);
    }

    /**
     * 失败响应
     * @param errorCode 错误码
     * @return 响应
     */
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败响应，带自定义消息
     * @param errorCode 错误码
     * @param msg 自定义消息
     * @return 响应
     */
    public static BaseResponse<?> error(ErrorCode errorCode, String msg) {
        return new BaseResponse<>(errorCode, msg);
    }

    /**
     * 失败响应，自定义错误码和消息
     * @param code    错误码
     * @param msg 错误信息
     * @return 响应
     */
    public static BaseResponse<?> error(int code, String msg) {
        return new BaseResponse<>(code, null, msg);
    }

    /**
     * 无数据的成功响应
     * @return 响应
     */
    public static BaseResponse<?> success() {
        return new BaseResponse<>(0, null, "ok");
    }
}