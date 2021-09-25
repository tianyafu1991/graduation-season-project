package com.graduation.mapper;

import com.graduation.domain.BookCategory;
import com.graduation.domain.param.BookCategoryParams;

import java.util.List;

public interface BookCategoryMapper {
    // 获取所有的书籍分类
    List<BookCategory> getAllBookCategories();
    // 按照查询条件分页查询书籍类别
    List<BookCategory> getBookCategoryLists(BookCategoryParams bookCategoryParams);
    // 按照查询条件 获取符合条件的所有书籍类别的总数
    Long getCategoryTotalCount(BookCategoryParams bookCategoryParams);
    // 新增书籍类别
    void addBookCategory(BookCategory bookCategory);
    // 编辑书籍类别时 需要先获取到该类别的信息
    BookCategory getBookCategoryById(Integer categoryId);
    // 更新书籍类别信息
    void updateBookCategory(BookCategory bookCategory);
    // 批量删除书籍类别
    void deleteBookCategoriesByIds(String categoryIds);
}
