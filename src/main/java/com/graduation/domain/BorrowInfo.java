package com.graduation.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 借阅信息
 */
@Data
public class BorrowInfo {
    // 自增主键id
    private Integer id;
    // 用户id
    private Integer userId;
    // 书籍id
    private Integer bookId;
    // 借书时间 yyyy-MM-dd HH:mm:ss
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String borrowTime;
    // 需要还书的时间 yyyy-MM-dd HH:mm:ss
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String returnTime;
    // 实际还书的时间 yyyy-MM-dd HH:mm:ss
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String realReturnTime;
    // 归还标记 0未归还  1已归还
    private String returnFlg;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient String createTime;
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private transient String updateTime;
}
