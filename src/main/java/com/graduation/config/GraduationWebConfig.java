package com.graduation.config;

import com.graduation.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * config类  用于配置拦截器 配置拦截请求和需放行请求
 * 一般为登录页 验证码  前端静态资源是需要放行的
 * 其它都需要拦截 目前拦截主要是判断session中是否有用户信息 以此作为是否登录的依据
 */
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
