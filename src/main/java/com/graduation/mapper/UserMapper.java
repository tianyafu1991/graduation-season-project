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
    // 添加用户
    void addUser(User user);
    // 根据传入的ids批量删除用户
    void deleteUsersByIds(String ids);
    // 编辑用户信息
    void updateUser(User user);
    // 根据用户id查询用户信息
    User getUserById(Integer id);
    // 根据用户名查询用户
    User getUserByUserName(String username);
}
