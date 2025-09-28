package com.jhc.employee_management.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhc.employee_management.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    @Resource
    private MessageSource messageSource;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        log.warn("【ACCESS DENIED】无权限访问：{}", request.getRequestURI());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String msg = messageSource.getMessage("access.denied", null, LocaleContextHolder.getLocale());
        ApiResponse<Object> res = ApiResponse.error(403, msg);

        response.getWriter().write(new ObjectMapper().writeValueAsString(res));
    }
}
