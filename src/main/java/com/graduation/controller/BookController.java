package com.graduation.controller;

import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
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


}
