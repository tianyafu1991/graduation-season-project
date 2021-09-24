package com.graduation.config;

import com.graduation.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GraduationWebConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);
        // 拦截请求
        interceptorRegistration.addPathPatterns("/**");
        // 放行的请求
        interceptorRegistration.excludePathPatterns(
                "/",
                "/captcha",
                "/user/login",
                "/user/logon",
                "/layuimini/**"
        );
    }
}
