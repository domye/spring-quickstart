package com.domye.demo.handler;

import com.domye.demo.common.BaseResponse;
import com.domye.demo.common.ResultUtils;
import com.domye.demo.exception.ErrorCode;
import com.domye.demo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e, HttpServletRequest request) {
        log.error("BusinessException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        log.error("Request URI: {}, Error Code: {}, Description: {}", requestUri, e.getCode(), e.getDescription());
        return ResultUtils.error(e.getCode(), e.getDescription());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error("Request URI: {}, Validation Error: {}", requestUri, errorMsg);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR.getCode(), "参数校验失败: " + errorMsg);
    }

    /**
     * 处理参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse<?> bindExceptionHandler(BindException e, HttpServletRequest request) {
        log.error("BindException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error("Request URI: {}, Bind Error: {}", requestUri, errorMsg);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR.getCode(), "参数绑定失败: " + errorMsg);
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("MethodArgumentTypeMismatchException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        String errorMsg = String.format("参数 [%s] 类型不匹配，期望类型为 [%s]，实际类型为 [%s]", 
                e.getName(), e.getRequiredType(), e.getValue());
        log.error("Request URI: {}, Type Mismatch Error: {}", requestUri, errorMsg);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR.getCode(), "参数类型错误: " + errorMsg);
    }

    /**
     * 处理请求体无法读取异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        log.error("Request URI: {}, Message Not Readable Error: {}", requestUri, e.getMessage());
        return ResultUtils.error(ErrorCode.PARAMS_ERROR.getCode(), "请求体格式错误，请检查参数格式");
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e, HttpServletRequest request) {
        log.error("RuntimeException: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        log.error("Request URI: {}, RuntimeException: {}", requestUri, e.getMessage());
        
        // 根据不同类型的运行时异常进行处理
        if (e instanceof IllegalArgumentException) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "参数错误: " + e.getMessage());
        } else if (e instanceof NullPointerException) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "空指针异常: " + e.getMessage());
        }
        
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误: " + e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse<?> exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("Exception: {}", e.getMessage(), e);
        String requestUri = request.getRequestURI();
        log.error("Request URI: {}, Unknown Error: {}", requestUri, e.getMessage());
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统未知异常: " + e.getMessage());
    }
}