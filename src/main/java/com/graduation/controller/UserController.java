package com.graduation.controller;

import cn.hutool.core.util.StrUtil;
import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.User;
import com.graduation.domain.param.BookParams;
import com.graduation.domain.param.PasswordParams;
import com.graduation.domain.param.UserParams;
import com.graduation.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
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



    /**
     * 按照条件获取用户列表
     * @param userParams
     * @return
     */
    @GetMapping("/getUserLists")
    @ResponseBody
    public CommonResponse<List<Book>> getUserLists(UserParams userParams){
        // 按照查询条件分页查询用户
        List<User> users = userService.getUserLists(userParams);
        // 按照查询条件 获取符合条件的所有数据的总数
        Long totalCount = userService.getTotalCount(userParams);
        CommonResponse response = CommonResponse.successInstance(users, totalCount);
        return response;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public CommonResponse<User> addUser(User user){
        User userInfo = userService.addUser(user);
        return CommonResponse.successInstance(userInfo,null);
    }

    /**
     * 根据传入的ids批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteUsersByIds/{ids}")
    @ResponseBody
    public CommonResponse<User> deleteUsersByIds(@PathVariable String ids){
        userService.deleteUsersByIds(ids);
        return CommonResponse.successInstance("删除成功");
    }

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    @ResponseBody
    public CommonResponse<User> updateUser(User user){
        userService.updateUser(user);
        return CommonResponse.successInstance("修改成功");
    }


    /**
     * 修改用户密码
     * @param passwordParams
     * @return
     */
    @PutMapping("/updateUserPassword")
    @ResponseBody
    public CommonResponse<User> updateUserPassword(PasswordParams passwordParams){
        if(StrUtil.isBlank(passwordParams.getOldPassword())|| StrUtil.isBlank(passwordParams.getNewPassword()) || StrUtil.isBlank(passwordParams.getAgainPassword())){
            return CommonResponse.errorInstance("没有填写密码");
        }else if (!passwordParams.getNewPassword().equals(passwordParams.getAgainPassword())){
            return CommonResponse.errorInstance("2次新的密码不一致");
        }else {
            // 通过用户id获取表中的用户信息
            User userById = userService.getUserById(passwordParams.getId());
            // 判断表中的用户密码与前端传入的密码是否一致
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 第一个参数是前端页面传来的密码  第二个参数是数据库中的密码
            if (!passwordEncoder.matches(passwordParams.getOldPassword(), userById.getUserPassword())) {
                return CommonResponse.errorInstance("旧密码错误");
            }
            //一致就进行改密码操作
            String newPasswordEncoded = passwordEncoder.encode(passwordParams.getNewPassword());
            passwordParams.setNewPassword(newPasswordEncoded);
            userService.updateUserPassword(passwordParams);
            return CommonResponse.successInstance("修改成功");
        }

    }
}
