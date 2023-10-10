package com.hung.ch6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hung.ch6.interceptor.LoginInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =
                registry.addInterceptor(new LoginInterceptor());
        // 所有路徑都檢查
        registration.addPathPatterns("/**");
        // 添加不檢查路徑
        registration.excludePathPatterns(
                "/user/login",
                "/articles/**",
                "/**/*.js",
                "/**/*.css",
                "/static/**");
    }
}
