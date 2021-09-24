package com.graduation.domain;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private transient Integer id;
    // 用户名
    private String username;
    // 密码
    private String userPassword;

    private String userPhone;

    private String adminFlg;

    private transient String createTime;

    private transient String updateTime;
}
