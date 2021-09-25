package com.graduation.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

/**
 * 用户信息
 */
@Data
public class User implements Serializable {
    // 编号
    private transient Integer id;
    // 用户名
    private String username;
    // 密码
    private String userPassword;
    // 手机号
    private String userPhone;
    // 是否是管理员标记位 0否  1是
    private String adminFlg;
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient String createTime;
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient String updateTime;
}
