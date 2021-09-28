package com.graduation.service;

import com.graduation.domain.User;
import com.graduation.domain.param.PasswordParams;
import com.graduation.domain.param.UserParams;

import java.util.List;
/**
 * 用户相关的service接口
 */
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

    /**
     *  添加用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 根据传入的ids批量删除用户
     * @param ids
     */
    void deleteUsersByIds(String ids);

    /**
     * 编辑用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 修改用户密码
     * @param passwordParams
     */
    void updateUserPassword(PasswordParams passwordParams);

    /**
     * 根据用户信息查询用户
     * 主要用于编辑用户的校验 编辑用户时 如果传入的用户名不为空 则传入的username应该要和除带更新用户id的那条数据外的所有用户名都不重复
     * @param user
     * @return
     */
    User getUserByUser(User user);
}
