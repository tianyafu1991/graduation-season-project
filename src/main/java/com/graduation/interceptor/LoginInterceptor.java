package com.graduation.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器  防止页面直接跨过登录页面调用后台的接口
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (null != user) {
            // 有登录信息
            log.debug("已登录 请求放行:" + request.getRequestURI());
            return true;
        } else {
            // 没有登录信息 重定向到登录页面
            log.debug("未登录的请求:" + request.getRequestURI());
            response.sendRedirect(request.getContextPath());
            return false;
        }
    }
}
