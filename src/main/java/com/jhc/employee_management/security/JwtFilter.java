package com.jhc.employee_management.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Resource private JwtUtil jwtUtil;
    @Resource private CustomUserDetailsService userDetailsService;
    @Resource private TokenBlacklistService tokenBlacklistService;
    @Resource private MessageSource messageSource;

    // ★ 白名单前缀（注意：这里是“去掉 context-path 后”的路径）
    private static final List<String> EXCLUDE_PREFIXES = Arrays.asList(
            "/auth/", "/public/", "/favicon.ico",
            "/upload/img/upload/", "/access/img/upload/",
            "/api/exchange/" ,// 开发期放开交流区
            "/employee_management/uploads/", "/upload/uploads/", "/access/uploads/","/uploads/"// 交流区图片
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // 例: /employee_management/api/exchange/posts -> 切成 /api/exchange/posts
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String path = uri.startsWith(contextPath) ? uri.substring(contextPath.length()) : uri;

        if (isExcluded(path)) {
            chain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);

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

    private boolean isExcluded(String path) {
        if ("/api/exchange".equals(path) || "/api/exchange/".equals(path)) return true;
        for (String p : EXCLUDE_PREFIXES) if (path.startsWith(p)) return true;
        return false;
    }

    private void writeUnauthorized(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String msg = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        com.jhc.employee_management.common.ApiResponse<Object> body =
                com.jhc.employee_management.common.ApiResponse.error(401, msg);
        response.getWriter().write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(body));
    }
}
