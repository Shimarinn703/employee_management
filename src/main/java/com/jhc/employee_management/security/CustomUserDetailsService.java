package com.jhc.employee_management.security;

import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.entity.UserPermissions;
import com.jhc.employee_management.service.UserLoginInfoService;
import com.jhc.employee_management.service.UserPermissionsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserLoginInfoService userLoginInfoService;

    @Resource
    private UserPermissionsService userPermissionsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginInfo user = userLoginInfoService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 权限信息
        UserPermissions userPermissions = userPermissionsService.getByEmployeeId(user.getEmployeeId());
        if (userPermissions == null) {
            throw new RuntimeException("用户权限未配置");
        }

        // 将 permission_level 映射成 Spring Security 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userPermissions.getPermissionLevel() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
