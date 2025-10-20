package com.jhc.employee_management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
    @Value("${file.upload-path}")
    private String uploadDir;
    
    @Value("${file.access-path}")
    private String accesdDir;

    // 1. 保留原有的资源映射功能（来自MvcConfig）
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /img/upload/** 的请求映射到外部目录
        registry.addResourceHandler(accesdDir + "**")
                .addResourceLocations("file:" + uploadDir);
    }

    // 2. 新增CORS跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","DELETE","PUT","PATCH")
                .allowedHeaders("*");
    }
}