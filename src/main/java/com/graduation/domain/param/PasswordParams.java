package com.graduation.domain.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码的params
 */
@Data
public class PasswordParams implements Serializable {
    // 用户id
    private Integer id;
    // 旧密码
    private String oldPassword;
    //新密码
    private String newPassword;
    // 新的密码确认
    private String againPassword;


}
