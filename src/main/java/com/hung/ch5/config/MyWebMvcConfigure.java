package com.hung.ch5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hung.ch5.interceptor.MyInterceptor;

@Configuration
public class MyWebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =
                registry.addInterceptor(new MyInterceptor());
        // 所有路徑都被攔截
        //registration.addPathPatterns("/**");
        registration.addPathPatterns("/user/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
