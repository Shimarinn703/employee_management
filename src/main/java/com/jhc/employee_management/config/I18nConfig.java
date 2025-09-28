package com.jhc.employee_management.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class I18nConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:messages"); // 对应 messages_ja.properties / messages_zh_CN.properties
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        // 可选：避免再抛异常，找不到key时就返回key本身
        // ms.setUseCodeAsDefaultMessage(true);
        return ms;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver r = new AcceptHeaderLocaleResolver();
        r.setDefaultLocale(Locale.JAPAN); // 默认日语
        return r;
    }
}
