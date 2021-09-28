package com.graduation.controller;

import cn.hutool.core.util.StrUtil;
import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.BookWithBorrowFlg;
import com.graduation.domain.User;
import com.graduation.domain.param.BookCategoryParams;
import com.graduation.domain.param.BookParams;
import com.graduation.service.BookService;
import com.graduation.service.UserService;
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

    @Autowired
    private UserService userService;

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

    /**
     * 新增书籍 只有管理员可以新增书籍
     * 书名是可以重复的  但书的编号是不能重复的
     * @param book
     * @return
     */
    @PostMapping("/addBook")
    @ResponseBody
    public CommonResponse<Book> addBook(Book book){
        //必要的非空校验
        if(null == book || StrUtil.isBlank(book.getBookNo()) || StrUtil.isBlank(book.getBookName())|| StrUtil.isBlank(book.getBookAuthor())|| StrUtil.isBlank(book.getBookPublish())|| StrUtil.isBlank(book.getBookPrice())){
            return CommonResponse.errorInstance("参数传参错误");
        }
        // 书的编号是不能重复的 所以这里做重复校验
        Book book1 = bookService.getBookByBookNo(book.getBookNo());
        if(null != book1){
            return CommonResponse.errorInstance("该编号已存在 请重新输入");
        }
        Book bookInfo = bookService.addBook(book);
        return CommonResponse.successInstance(bookInfo,null);
    }

    /**
     * 删除书籍 只有管理员可以删除书籍  这里是物理删 不使用逻辑删 因为本身只有管理员可以删除书籍 没必要做成逻辑删
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBooksByIds/{ids}")
    @ResponseBody
    public CommonResponse<Book> deleteBooksByIds(@PathVariable String ids){
        bookService.deleteBooksByIds(ids);
        return CommonResponse.successInstance("删除成功");
    }

    /**
     * 修改书籍 只有管理员可以修改书籍
     * @param book
     * @return
     */
    @PutMapping("/updateBook")
    @ResponseBody
    public CommonResponse<Book> updateBook(Book book){
        if(null == book || null == book.getId()){
            return CommonResponse.errorInstance("参数传参错误");
        }
        Book book1 = bookService.getBookByBook(book);
        if(null != book1){
            return CommonResponse.errorInstance("书籍编号已存在 请重新输入");
        }
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
     * 新增书籍类别 只有管理员可以新增书籍类别
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
        if(null == bookCategory || null == bookCategory.getCategoryId()){
            return CommonResponse.errorInstance("参数传参错误");
        }
        // 更新类别名称也不能与已有的类别名称重名(这个已有的类别名称不能包括要更新的那条数据的名称)
        BookCategory bookCategoryByCategoryName = bookService.getBookCategoryByBookCategory(bookCategory);
        if(null != bookCategoryByCategoryName){
            return CommonResponse.errorInstance("该类别已存在 请重新输入");
        }
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
     * 普通用户-按照条件获取书籍信息
     * @param bookParams
     * @return
     */
    @GetMapping("/getBookListsWithUser/{username}")
    @ResponseBody
    public CommonResponse<List<BookWithBorrowFlg>> getBookListsWithUser(@PathVariable String username,BookParams bookParams){
        User user = userService.getUserByUserName(username);
        if(null == user){
            return CommonResponse.errorInstance("参数传参错误");
        }
        // 按照查询条件分页查询书籍
        List<BookWithBorrowFlg> books = bookService.getBookListsWithUser(bookParams,user);
        // 按照查询条件 获取符合条件的所有数据的总数
        Long totalCount = bookService.getTotalCount(bookParams);
        CommonResponse response = CommonResponse.successInstance(books, totalCount);
        return response;
    }

}
