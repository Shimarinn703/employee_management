package com.jhc.employee_management.exception;

import com.jhc.employee_management.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> handleBusinessException(BusinessException ex) {
        log.warn("【BusinessException】{}", ex.getMessage());
        String msg = resolveMsg(ex.getMessage());
        return ApiResponse.error(400, msg);
    }

    @ExceptionHandler(SystemException.class)
    public ApiResponse<?> handleSystemException(SystemException ex) {
        log.error("【SystemException】{}", ex.getMessage(), ex);
        String msg = resolveMsg("error.unexpected");
        return ApiResponse.error(500, msg);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOtherException(Exception ex) {
        log.error("【UnknownException】{}", ex.getMessage(), ex);
        String msg = resolveMsg("error.unexpected");
        return ApiResponse.error(500, msg);
    }


    private String resolveMsg(String keyOrLiteral) {
        try {
            return messageSource.getMessage(keyOrLiteral, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ignore) {
            return keyOrLiteral; // 传来不是key时，原样返回
        }
    }
}