package com.graduation.controller;

import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.service.BookService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * url 路由控制器
 */
@Controller
public class RouteController {

    @Autowired
    private BookService bookService;

    // 首页跳转 跳转到登录页
    @GetMapping("/")
    public String login(){
        return "login";
    }
    // 登录页中的验证码获取
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    // 不管是普通用户还是admin 登录后的首页中 都展示这个欢迎页面
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    // 普通用户登录后 登录到普通用户的首页
    @GetMapping("/index")
    public String index(){
        return "user/index";
    }

    // admin登录后 登录到admin的首页
    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "admin/adminIndex";
    }


    // admin用户的 借阅信息 页面跳转
    @GetMapping("/adminBorrowList")
    public String adminBorrowList(){
        return "admin/borrowList";
    }

    // admin用户的 书籍管理 页面跳转
    @GetMapping("/adminBookList")
    public String adminBookList(){
        return "admin/bookList";
    }

    // admin用户的 书籍类别管理 页面跳转
    @GetMapping("/adminCategoryList")
    public String adminCategoryList(){
        return "admin/categoryList";
    }

    // admin用户的 用户管理 页面跳转
    @GetMapping("/adminUserList")
    public String adminUserList(){
        return "admin/userList";
    }


    // admin用户的 添加书籍 页面跳转
    @GetMapping("book/adminAddBook")
    public String adminAddBook(Model model){
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList",categoryList);
        return "admin/bookAdd";
    }

    @GetMapping("book/adminEditBook/{id}")
    public String getBooksById(@PathVariable Integer id, Model model){
        Book book = bookService.getBooksById(id);
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("book",book);
        model.addAttribute("categoryList",categoryList);
        return "admin/bookEdit";
    }

}
