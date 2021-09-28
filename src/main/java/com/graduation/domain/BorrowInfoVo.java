package com.graduation.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BorrowInfoVo implements Serializable {
    // 书籍编号
    private String bookNo;
    // 书籍名称
    private String bookName;
    // 书籍作者
    private String bookAuthor;
    // 书籍出版社
    private String bookPublish;
    // 书籍价格
    private String bookPrice;
    // 书籍类别名称
    private String categoryName;
    //  借书时间
    private String borrowTime;
    // 还书时间
    private String realReturnTime;
    // 逾期计费
    private String overDueCost;
    // 书籍介绍
    private String bookIntroduction;
    // 书籍id
    private String bookId;
    // 用户id
    private String userId;
    // 需要还书的时间
    private String returnTime;
    // 归还标记 0未归还  1已归还
    private String returnFlg;
    // 逾期标记 0未逾期  1已逾期  该标记仅针对未归还的书籍有效
    private String overDueFlg;
    // 用户名
    private String username;


}
