package com.graduation.service;

import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.param.BookParams;

import java.util.List;

public interface BookService {
    /**
     * 按照查询条件分页查询书籍
     * @param bookParams
     * @return
     */
    List<Book> getBookLists(BookParams bookParams);

    /**
     * 按照查询条件 获取符合条件的所有数据的总数
     * @param bookParams
     * @return
     */
    Long getTotalCount(BookParams bookParams);

    /**
     * 新增书籍
     * @param book
     * @return
     */
    Book addBook(Book book);

    /**
     * 获取所有的书籍分类
     * @return
     */
    List<BookCategory> getAllBookCategories();

    /**
     * 根据传入的ids 删除对应的书籍
     * @param ids
     */
    void deleteBooksByIds(String ids);

    /**
     * 根据传入的id 获取对应的书籍
     * @param id
     * @return
     */
    Book getBooksById(Integer id);

    /**
     * 编辑书籍
     * 根据传入的书籍信息 修改表中的信息
     * @param book
     */
    void updateBook(Book book);
}
