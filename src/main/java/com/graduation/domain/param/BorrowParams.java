package com.graduation.domain.param;

import com.graduation.domain.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BorrowParams extends Page implements Serializable {

    // 书籍编号
    private String bookNo;
    // 书籍名称
    private String bookName;
    // 书籍作者
    private String bookAuthor;
    // 书籍出版社
    private String bookPublish;
    // 书的分类
    private Integer bookCategory;

    private String startDate;
    private String endDate;

    // 逾期标记 0未逾期  1已逾期  该标记仅针对未归还的书籍有效
    private String overDueFlg;
}
