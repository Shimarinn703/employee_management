package com.jhc.employee_management.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jhc.employee_management.security.CustomUserDetailsService;
import com.jhc.employee_management.security.JwtAccessDeniedHandler;
import com.jhc.employee_management.security.JwtAuthenticationEntryPoint;
import com.jhc.employee_management.security.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource private JwtFilter jwtFilter;
    @Resource private CustomUserDetailsService userDetailsService;
    @Resource private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Resource private JwtAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
                // ★ 开发期放开交流区 API
                .antMatchers("/api/exchange/**").permitAll()
                // 交流静态资源放行
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("${app.upload.url}**").permitAll()
                // 已有白名单
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(
                    "/auth/login", "/auth/register", "/auth/captcha",
                    "/public/**", "/favicon.ico",
                    "/upload/img/upload/**", "/access/img/upload/**"
                ).permitAll()

                .anyRequest().authenticated()
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
