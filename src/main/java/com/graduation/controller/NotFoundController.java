package com.graduation.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一异常处理的controller
 * 这里将所有异常 统一给个404页面
 */
@Controller
public class NotFoundController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String notFoundError(){
        return "404";
    }
}
