package com.graduation.service;

import com.graduation.domain.User;
import com.graduation.domain.param.UserParams;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 按照查询条件分页查询用户
     * @param userParams
     * @return
     */
    List<User> getUserLists(UserParams userParams);

    /**
     * 按照查询条件 获取符合条件的所有数据的总数
     * @param userParams
     * @return
     */
    Long getTotalCount(UserParams userParams);
}
