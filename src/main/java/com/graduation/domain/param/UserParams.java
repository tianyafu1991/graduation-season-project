package com.graduation.domain.param;

import com.graduation.domain.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户查询的param
 */
@Data
public class UserParams  extends Page implements Serializable {

    // 用户名
    private String username;
    // 手机号
    private String userPhone;
    // 是否是管理员标记位 0否  1是
    private String adminFlg;
}
