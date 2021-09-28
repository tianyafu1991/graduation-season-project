package com.graduation.service.impl;

import com.graduation.domain.User;
import com.graduation.domain.param.PasswordParams;
import com.graduation.domain.param.UserParams;
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
     * 根据用户信息查询用户
     * 主要用于编辑用户的校验 编辑用户时 如果传入的用户名不为空 则传入的username应该要和除带更新用户id的那条数据外的所有用户名都不重复
     *
     * @param user
     * @return
     */
    @Override
    public User getUserByUser(User user) {
        return userMapper.getUserByUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param passwordParams
     */
    @Override
    public void updateUserPassword(PasswordParams passwordParams) {
        User user = new User();
        user.setId(passwordParams.getId());
        user.setUserPassword(passwordParams.getNewPassword());
        userMapper.updateUser(user);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName( username);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    /**
     * 根据传入的ids批量删除用户
     *
     * @param ids
     */
    @Override
    public void deleteUsersByIds(String ids) {
        userMapper.deleteUsersByIds(ids);
    }

    /**
     * 编辑用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        // 初始密码不是由页面配置的 而是代码中写死的123456
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userPassword = passwordEncoder.encode("123456");
        user.setUserPassword(userPassword);
        userMapper.addUser(user);
        return user;
    }

    /**
     * 按照查询条件分页查询用户
     *
     * @param userParams
     * @return
     */
    @Override
    public List<User> getUserLists(UserParams userParams) {
        return userMapper.getUserLists(userParams);
    }

    /**
     * 按照查询条件 获取符合条件的所有数据的总数
     *
     * @param userParams
     * @return
     */
    @Override
    public Long getTotalCount(UserParams userParams) {
        return userMapper.getTotalCount(userParams);
    }

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
