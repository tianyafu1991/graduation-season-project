package com.graduation.service.impl;

import com.graduation.domain.User;
import com.graduation.mapper.UserMapper;
import com.graduation.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cn.hutool.core.collection.CollectionUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User result = null;
        // 根据用户名和是否admin的标识位去查找用户
        User user1 = userMapper.getUser(user);
        if (null != user1) {
            // 使用spring-security提供的类进行密码判断
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 第一个参数是前端页面传来的密码  第二个参数是数据库中的密码
            if (passwordEncoder.matches(user.getUserPassword(), user1.getUserPassword())) {
                result = user1;
            }
        }
        return result;
    }
}
