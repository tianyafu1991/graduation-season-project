package com.graduation.mapper;

import com.graduation.domain.Book;
import com.graduation.domain.param.BookParams;

import java.util.List;

public interface BookMapper {

    // 按照查询条件分页查询书籍
    List<Book> getBookLists(BookParams bookParams);

    // 按照查询条件 获取符合条件的所有数据的总数
    Long getTotalCount(BookParams bookParams);

    void addBook(Book book);
    // 根据传入的ids 删除对应的书籍
    void deleteBooksByIds(String ids);

    // 根据传入的id 获取对应的书籍
    Book getBooksById(Integer id);
    // 根据传入的书籍信息 修改表中的信息
    void updateBook(Book book);
}
