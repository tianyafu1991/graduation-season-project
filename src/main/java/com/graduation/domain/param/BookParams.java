package com.graduation.domain.param;

import com.graduation.domain.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 书籍查询的param
 */
@Data
public class BookParams extends Page implements Serializable {

    // 书的编号
    private String bookNo;
    // 书名
    private String bookName;
    // 书的作者
    private String bookAuthor;
    // 书的出版社
    private String bookPublish;
    // 书的分类
    private Integer bookCategory;

}
