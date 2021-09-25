package com.graduation.domain.param;

import com.graduation.domain.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 书籍类别查询的param
 */
@Data
public class BookCategoryParams extends Page implements Serializable {

    // 书的类别名称
    private String categoryName;

}
