package com.graduation.service;

import com.graduation.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
