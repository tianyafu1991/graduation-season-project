package com.graduation.mapper;

import com.graduation.domain.User;
import com.graduation.domain.param.UserParams;

import java.util.List;

public interface UserMapper {


    User getUser(User user);
    // 按照查询条件分页查询用户
    List<User> getUserLists(UserParams userParams);
    // 按照查询条件 获取符合条件的所有数据的总数
    Long getTotalCount(UserParams userParams);
}
