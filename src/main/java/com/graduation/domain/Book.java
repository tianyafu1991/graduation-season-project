package com.graduation.domain;

import lombok.Data;

/**
 * 书籍信息类
 */
@Data
public class Book{
    // 自增id
    private Integer id;
    // 书的编号
    private String bookNo;
    // 书名
    private String bookName;
    // 作者
    private String bookAuthor;
    // 书的出版社
    private String bookPublish;
    // 书的分类
    private Integer bookCategory;
    // 书的价格
    private String bookPrice;
    // 书的介绍
    private String bookIntroduction;
    // 书的分类名称
    private String categoryName;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
}
