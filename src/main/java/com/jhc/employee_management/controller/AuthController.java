package com.jhc.employee_management.controller;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.LoginRequest;
import com.jhc.employee_management.dto.RegisterRequest;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.entity.UserPermissions;
import com.jhc.employee_management.exception.BusinessException;
import com.jhc.employee_management.exception.SystemException;
import com.jhc.employee_management.security.JwtUtil;
import com.jhc.employee_management.service.UserLoginInfoService;
import com.jhc.employee_management.service.UserPermissionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            long expiresIn = jwtUtil.getExpirationSeconds();

            // 提取角色
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("username", userDetails.getUsername());
            userInfo.put("roles", roles);

            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("expiresIn", expiresIn);
            result.put("user", userInfo);

            return ResponseEntity.ok(ApiResponse.success("ログイン成功", result));

        } catch (BadCredentialsException e) {
            throw new BusinessException("ユーザー名またはパスワードが正しくありません");
        }
    }


    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userLoginInfoService.getByUsername(request.getUsername()) != null) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }

        // 保存用户登录信息
        UserLoginInfo user = new UserLoginInfo();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmployeeId(request.getEmployeeId());

        String employeeId = userLoginInfoService.generateNextEmployeeId();
        user.setEmployeeId(employeeId);

        userLoginInfoService.save(user);

        // 同时写入权限表（默认普通用户）
        UserPermissions permissions = new UserPermissions();
        permissions.setEmployeeId(employeeId);
        permissions.setPermissionLevel(1);
        permissions.setUpdatedAt(new Date());

        userPermissionsService.save(permissions);

        return ResponseEntity.ok("注册成功");
    }

    //测试
    @GetMapping("/api/test")
    public String testMethod() {
        throw new SystemException("11111");

    }


}