package com.graduation.service;

import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.BookWithBorrowFlg;
import com.graduation.domain.User;
import com.graduation.domain.param.BookCategoryParams;
import com.graduation.domain.param.BookParams;

import java.util.List;

/**
 * 书籍及书籍类别相关的service接口
 */
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

    /**
     * 按照查询条件分页查询书籍类别
     * @param bookCategoryParams
     * @return
     */
    List<BookCategory> getBookCategoryLists(BookCategoryParams bookCategoryParams);

    /**
     * 按照查询条件 获取符合条件的所有书籍类别的总数
     * @param bookCategoryParams
     * @return
     */
    Long getCategoryTotalCount(BookCategoryParams bookCategoryParams);

    /**
     * 新增书籍类别
     * @param bookCategory
     * @return
     */
    BookCategory addBookCategory(BookCategory bookCategory);

    /**
     * 编辑书籍类别时 需要先获取到该类别的信息
     * @param id
     * @return
     */
    BookCategory getBookCategoryById(Integer categoryId);

    /**
     * 更新书籍类别信息
     * @param bookCategory
     */
    void updateBookCategory(BookCategory bookCategory);

    /**
     * 批量删除书籍类别
     * @param categoryIds
     */
    void deleteBookCategoriesByIds(String categoryIds);

    /**
     * 查询书籍列表 以及标记该本书用户当前是否已经借了且尚未归还
     * @param bookParams
     * @param user
     * @return
     */
    List<BookWithBorrowFlg> getBookListsWithUser(BookParams bookParams, User user);

    /**
     * 根据类别名称获取书籍类别
     * 书籍类别重名校验
     * @param categoryName
     * @return
     */
    BookCategory getBookCategoryByCategoryName(String categoryName);

    /**
     * 根据书的编号获取书籍信息
     * @param bookNo
     * @return
     */
    Book getBookByBookNo(String bookNo);

    /**
     * 根据书籍类别信息获取书籍类别
     * @param bookCategory
     * @return
     */
    BookCategory getBookCategoryByBookCategory(BookCategory bookCategory);

    /**
     * 根据书籍信息获取书籍
     * @param book
     * @return
     */
    Book getBookByBook(Book book);
}
