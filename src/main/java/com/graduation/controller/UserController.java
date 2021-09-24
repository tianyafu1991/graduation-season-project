package com.graduation.controller;

import com.graduation.common.CommonResponse;
import com.graduation.domain.User;
import com.graduation.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用于用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "userPassword", dataType = "String", required = true, value = "密码"),
            @ApiImplicitParam(paramType = "query", name = "adminFlg", dataType = "String", required = true, value = "是否是管理员标记位 0否  1是"),
    })
    public CommonResponse login(User paramUser, @RequestParam("captcha") String captcha, HttpServletRequest request, HttpSession session){

        if (!CaptchaUtil.ver(captcha, request)) {
            return CommonResponse.errorInstance("验证码不正确");
        }

        User user = userService.login(paramUser);
        if(null != user){// 登录成功
            // 将用户的密码去掉后 将user对象放到session中
            user.setUserPassword(null);
            session.setAttribute("user",user);
            return CommonResponse.successInstance();
        }
        return CommonResponse.errorInstance("用户名或密码错误 请重新登录");

    }

}
