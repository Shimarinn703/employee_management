package com.jhc.employee_management.security;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private CustomUserDetailsService userDetailsService;

    @Resource
    private TokenBlacklistService tokenBlacklistService;

    @Resource
    private MessageSource messageSource;

    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/auth/login",
            "/auth/register",
            "/auth/captcha",
            "/employee/preview",
            "/public/",        // 静态资源
            "/favicon.ico",
            "/upload/img/upload/",
            "/access/img/upload/"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        // 1) 放行无需鉴权的路径（适配 contextPath）
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());
        for (String p : EXCLUDE_PATHS) {
            if (path.equals(p) || path.startsWith(p)) {
                chain.doFilter(request, response);
                return;
            }
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);

            // 2) 黑名单拦截
            if (tokenBlacklistService.isBlacklisted(jwt)) {
                writeUnauthorized(request, response, "auth.required");
                return;
            }

            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                writeUnauthorized(request, response, "token.expired");
                return;
            } catch (Exception e) {
                writeUnauthorized(request, response, "token.invalid");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
                writeUnauthorized(request, response, "user.notfound");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    //统一返回401 + ApiResponse
    private void writeUnauthorized(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String msg = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        com.jhc.employee_management.common.ApiResponse<Object> body =
                com.jhc.employee_management.common.ApiResponse.error(401, msg);
        response.getWriter().write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(body));
    }

}