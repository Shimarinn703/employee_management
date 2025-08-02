package com.jhc.employee_management.exception;

import com.jhc.employee_management.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> handleBusinessException(BusinessException ex) {
        log.warn("【BusinessException】{}", ex.getMessage());
        return ApiResponse.error(400, ex.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public ApiResponse<?> handleSystemException(SystemException ex) {
        log.error("【SystemException】{}", ex.getMessage(), ex);
        return ApiResponse.error(500, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOtherException(Exception ex) {
        log.error("【UnknownException】{}", ex.getMessage(), ex);
        return ApiResponse.error(500, "予期しないエラーが発生しました");
    }
}