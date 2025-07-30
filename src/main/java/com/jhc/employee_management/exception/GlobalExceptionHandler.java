package com.jhc.employee_management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        log.warn("【BusinessException】{}", ex.getMessage());
        Map<String, Object> res = new HashMap<>();
        res.put("code", 400);
        res.put("type", "業務異常");
        res.put("message", ex.getMessage());
        return res;
    }

    @ExceptionHandler(SystemException.class)
    public Map<String, Object> handleSystemException(SystemException ex) {
        log.error("【SystemException】{}", ex.getMessage(), ex);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 500);
        res.put("type", "システムエラー");
        res.put("message", ex.getMessage());
        return res;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleOtherException(Exception ex) {
        log.error("【UnknownException】{}", ex.getMessage(), ex);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 500);
        res.put("type", "システムエラー");
        res.put("message", "予期しないエラーが発生しました");
        return res;
    }
}