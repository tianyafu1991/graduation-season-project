package com.graduation.mapper;

import com.graduation.domain.BookCategory;

import java.util.List;

public interface BookCategoryMapper {
    // 获取所有的书籍分类
    List<BookCategory> getAllBookCategories();

}
