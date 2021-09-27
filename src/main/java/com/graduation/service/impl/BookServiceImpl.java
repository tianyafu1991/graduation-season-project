package com.graduation.service.impl;


import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.BookWithBorrowFlg;
import com.graduation.domain.param.BookCategoryParams;
import com.graduation.domain.param.BookParams;
import com.graduation.mapper.BookCategoryMapper;
import com.graduation.mapper.BookMapper;
import com.graduation.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookCategoryMapper bookCategoryMapper;


    /**
     * 查询书籍列表 以及标记该本书用户当前是否已经借了且尚未归还
     *
     * @param bookParams
     * @param username
     * @return
     */
    @Override
    public List<BookWithBorrowFlg> getBookListsWithUser(BookParams bookParams,String username) {
        List<BookWithBorrowFlg> bookListsWithUser = bookMapper.getBookListsWithUser(bookParams, username);
        // 其实这里的用户名在前端暂时没用到 这里先放到对象中 防止后续要用
        for (BookWithBorrowFlg bookWithBorrowFlg : bookListsWithUser) {
            bookWithBorrowFlg.setUsername(username);
        }
        return bookListsWithUser;
    }

    /**
     * 批量删除书籍类别
     *
     * @param categoryIds
     */
    @Override
    public void deleteBookCategoriesByIds(String categoryIds) {
        bookCategoryMapper.deleteBookCategoriesByIds(categoryIds);
    }

    /**
     * 更新书籍类别信息
     *
     * @param bookCategory
     */
    @Override
    public void updateBookCategory(BookCategory bookCategory) {
        bookCategoryMapper.updateBookCategory(bookCategory);
    }

    /**
     * 编辑书籍类别时 需要先获取到该类别的信息
     *
     * @param categoryId
     * @return
     */
    @Override
    public BookCategory getBookCategoryById(Integer categoryId) {
        return bookCategoryMapper.getBookCategoryById( categoryId);
    }

    /**
     * 新增书籍类别
     *
     * @param bookCategory
     * @return
     */
    @Override
    public BookCategory addBookCategory(BookCategory bookCategory) {
        bookCategoryMapper.addBookCategory(bookCategory);
        return bookCategory;
    }

    /**
     * 按照查询条件分页查询书籍类别
     *
     * @param bookCategoryParams
     * @return
     */
    @Override
    public List<BookCategory> getBookCategoryLists(BookCategoryParams bookCategoryParams) {
        return bookCategoryMapper.getBookCategoryLists(bookCategoryParams);
    }

    /**
     * 按照查询条件 获取符合条件的所有书籍类别的总数
     *
     * @param bookCategoryParams
     * @return
     */
    @Override
    public Long getCategoryTotalCount(BookCategoryParams bookCategoryParams) {
        return bookCategoryMapper.getCategoryTotalCount(bookCategoryParams);
    }

    /**
     * 编辑书籍
     * 根据传入的书籍信息 修改表中的信息
     *
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    /**
     * 根据传入的id 获取对应的书籍
     *
     * @param id
     * @return
     */
    @Override
    public Book getBooksById(Integer id) {
        return bookMapper.getBooksById(id);
    }

    /**
     * 根据传入的ids 删除对应的书籍
     *
     * @param ids
     */
    @Override
    public void deleteBooksByIds(String ids) {
        bookMapper.deleteBooksByIds(ids);
    }

    /**
     * 获取所有的书籍分类
     *
     * @return
     */
    @Override
    public List<BookCategory> getAllBookCategories() {
        return bookCategoryMapper.getAllBookCategories();
    }

    /**
     * 新增书籍
     *
     * @param book
     * @return
     */
    @Override
    public Book addBook(Book book) {
        bookMapper.addBook(book);
        return book;
    }

    /**
     * 按照查询条件分页查询书籍
     *
     * @param bookParams
     * @return
     */
    @Override
    public List<Book> getBookLists(BookParams bookParams) {
        return bookMapper.getBookLists(bookParams);
    }

    /**
     * 按照查询条件 获取符合条件的所有数据的总数
     *
     * @param bookParams
     * @return
     */
    @Override
    public Long getTotalCount(BookParams bookParams) {
        return bookMapper.getTotalCount(bookParams);
    }
}
