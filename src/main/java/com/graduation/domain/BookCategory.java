package com.graduation.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 书分类信息
 */
@Data
public class BookCategory implements Serializable {
    // 书的分类id
    private Integer categoryId;
    // 书的分类名称
    private String categoryName;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
}
