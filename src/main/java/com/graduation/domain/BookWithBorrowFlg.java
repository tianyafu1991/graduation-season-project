package com.graduation.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 当这个用户已经借了某本书的时候 在查询书籍列表的这本书的借书按钮就应该置灰  即 不能重复借
 * 即当borrowFlg 为1是 借书按钮要置灰
 */
@Data
public class BookWithBorrowFlg extends Book implements Serializable {
    // 用户名
    private String username;

    // 0 表示该用户当前没有借 1表示该用户已经借了这本书 且还是未归还的
    private Integer borrowFlg;
}
