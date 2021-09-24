package com.graduation.service.impl;


import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
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
