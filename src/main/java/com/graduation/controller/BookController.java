package com.graduation.controller;

import cn.hutool.core.util.StrUtil;
import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.BookWithBorrowFlg;
import com.graduation.domain.param.BookCategoryParams;
import com.graduation.domain.param.BookParams;
import com.graduation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 按照条件获取书籍信息
     * @param bookParams
     * @return
     */
    @GetMapping("/getBookLists")
    @ResponseBody
    public CommonResponse<List<Book>> getBookLists(BookParams bookParams){
        // 按照查询条件分页查询书籍
        List<Book> books = bookService.getBookLists(bookParams);
        // 按照查询条件 获取符合条件的所有数据的总数
        Long totalCount = bookService.getTotalCount(bookParams);
        CommonResponse response = CommonResponse.successInstance(books, totalCount);
        return response;
    }

    @PostMapping("/addBook")
    @ResponseBody
    public CommonResponse<Book> addBook(Book book){
        Book bookInfo = bookService.addBook(book);
        return CommonResponse.successInstance(bookInfo,null);
    }


    @DeleteMapping("/deleteBooksByIds/{ids}")
    @ResponseBody
    public CommonResponse<Book> deleteBooksByIds(@PathVariable String ids){
        bookService.deleteBooksByIds(ids);
        return CommonResponse.successInstance("删除成功");
    }


    @PutMapping("/updateBook")
    @ResponseBody
    public CommonResponse<Book> updateBook(Book book){
        bookService.updateBook(book);
        return CommonResponse.successInstance("修改成功");
    }

    /**
     * 获取书籍分类信息
     * @param bookCategoryParams
     * @return
     */
    @GetMapping("/getBookCategoryLists")
    @ResponseBody
    public CommonResponse<List<Book>> getBookCategoryLists(BookCategoryParams bookCategoryParams){
        // 按照查询条件分页查询书籍类别
        List<BookCategory> categories = bookService.getBookCategoryLists(bookCategoryParams);
        // 按照查询条件 获取符合条件的所有书籍类别的总数
        Long totalCount = bookService.getCategoryTotalCount(bookCategoryParams);
        CommonResponse response = CommonResponse.successInstance(categories, totalCount);
        return response;
    }

    /**
     * 新增书籍类别
     * @param bookCategory
     * @return
     */
    @PostMapping("/addBookCategory")
    @ResponseBody
    public CommonResponse<BookCategory> addBookCategory(BookCategory bookCategory){
        // 页面上是做了类别名称必填校验的 所以以下2个非空校验 只是为了防止绕过前端页面直接调用后端接口
        if(null == bookCategory){
            return CommonResponse.errorInstance("参数传参错误");
        }
        if(StrUtil.isBlank(bookCategory.getCategoryName())){
            return CommonResponse.errorInstance("书籍类别不能为空");
        }
        // 书籍类别重名校验
        BookCategory category = bookService.getBookCategoryByCategoryName(bookCategory.getCategoryName());
        if(null != category){
            return CommonResponse.errorInstance("该类别已存在 请重新输入");
        }
        BookCategory bookCategoryInfo = bookService.addBookCategory(bookCategory);
        return CommonResponse.successInstance(bookCategoryInfo,null);
    }

    /**
     * 前端页面编辑书籍类别(只能更改类别名称) 后台做更新操作
     * @param bookCategory
     * @return
     */
    @PutMapping("/updateBookCategory")
    @ResponseBody
    public CommonResponse<BookCategory> updateBookCategory(BookCategory bookCategory){
        bookService.updateBookCategory(bookCategory);
        return CommonResponse.successInstance("修改成功");
    }

    /**
     * 批量删除书籍类别
     * @param categoryIds
     * @return
     */
    @DeleteMapping("/deleteBookCategoriesByIds/{categoryIds}")
    @ResponseBody
    public CommonResponse<Book> deleteBookCategoriesByIds(@PathVariable String categoryIds){
        bookService.deleteBookCategoriesByIds(categoryIds);
        return CommonResponse.successInstance("删除成功");
    }


    /**
     * 按照条件获取书籍信息
     * @param bookParams
     * @return
     */
    @GetMapping("/getBookListsWithUser/{username}")
    @ResponseBody
    public CommonResponse<List<BookWithBorrowFlg>> getBookListsWithUser(@PathVariable String username,BookParams bookParams){
        // 按照查询条件分页查询书籍
        List<BookWithBorrowFlg> books = bookService.getBookListsWithUser(bookParams,username);
        // 按照查询条件 获取符合条件的所有数据的总数
        Long totalCount = bookService.getTotalCount(bookParams);
        CommonResponse response = CommonResponse.successInstance(books, totalCount);
        return response;
    }

}
