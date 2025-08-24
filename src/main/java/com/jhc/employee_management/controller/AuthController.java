package com.jhc.employee_management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jhc.employee_management.security.TokenBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.LoginRequest;
import com.jhc.employee_management.dto.RegisterRequest;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.entity.UserPermissions;
import com.jhc.employee_management.exception.BusinessException;
import com.jhc.employee_management.security.JwtUtil;
import com.jhc.employee_management.service.UserLoginInfoService;
import com.jhc.employee_management.service.UserPermissionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserLoginInfoService userLoginInfoService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserPermissionsService userPermissionsService;

    @Resource
    private TokenBlacklistService tokenBlacklistService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        log.info("【LOGIN】尝试登录，用户名：{}", request.getUsername());
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            boolean remember = Boolean.TRUE.equals(request.getRememberMe());
            String token = jwtUtil.generateToken(userDetails, remember);

            //计算过期时间与剩余秒数
            Date expiresAt = jwtUtil.getExpirationDate(token);
            long expiresIn = Math.max(0, (expiresAt.getTime() - System.currentTimeMillis()) / 1000);

            // 提取角色
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("username", userDetails.getUsername());
            userInfo.put("roles", roles);

            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("expiresAt", expiresAt);
            result.put("expiresIn", expiresIn);
            result.put("user", userInfo);

            log.info("【LOGIN】登录成功，用户名：{}", request.getUsername());
            return ResponseEntity.ok(ApiResponse.success("ログイン成功", result));

        } catch (BadCredentialsException e) {
            log.warn("【LOGIN】认证失败（用户名或密码错误），用户名：{}", request.getUsername());
            throw new BusinessException("ユーザー名またはパスワードが正しくありません");
        }
    }


    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        log.info("【REGISTER】注册请求：用户名={}", request.getUsername());
        if (userLoginInfoService.getByUsername(request.getUsername()) != null) {
            log.warn("【REGISTER】用户名已存在：{}", request.getUsername());
            return ResponseEntity.ok(ApiResponse.error(100,"ユーザーが既に存在している"));
        }

        // 保存用户登录信息
        UserLoginInfo user = new UserLoginInfo();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String employeeId = userLoginInfoService.generateNextEmployeeId();
        user.setEmployeeId(employeeId);

        userLoginInfoService.save(user);

        // 同时写入权限表（默认普通用户）
        UserPermissions permissions = new UserPermissions();
        permissions.setEmployeeId(employeeId);
        permissions.setPermissionLevel(1);
        permissions.setUpdatedAt(new Date());

        userPermissionsService.save(permissions);

        log.info("【REGISTER】注册成功：用户名={}, employeeId={}", request.getUsername(), employeeId);
        return ResponseEntity.ok(ApiResponse.success("登録成功", null));
    }

    //测试
    @GetMapping("/api/test")
    public String testMethod() throws Exception {
        throw new Exception("11111");

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Date exp = jwtUtil.getExpirationDate(token);
                long remain = Math.max(0, exp.getTime() - System.currentTimeMillis());
                tokenBlacklistService.blacklist(token, remain);
            } catch (Exception ignored) {}
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(ApiResponse.success("ログアウト成功", null));
    }
   
}
