package com.graduation.controller;

import com.graduation.domain.Book;
import com.graduation.domain.BookCategory;
import com.graduation.domain.User;
import com.graduation.service.BookService;
import com.graduation.service.UserService;
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

    @Autowired
    private UserService userService;

    /**
     * 首页跳转 跳转到登录页
     * @return
     */
    @GetMapping("/")
    public String login() {
        return "login";
    }

    /**
     * 首页跳转 跳转到登录页
     * @param request
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }

    /**
     * 跳转到修改密码页面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("common/userPassword/{username}")
    public String userPassword(@PathVariable String username, Model model) {
        User user = userService.getUserByUserName(username);
        model.addAttribute("user", user);
        return "common/userPassword";
    }

    /**
     * 跳转到基本资料页面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("common/userSetting/{username}")
    public String userSetting(@PathVariable String username, Model model) {
        User user = userService.getUserByUserName(username);
        model.addAttribute("user", user);
        return "common/userSetting";
    }

    /**
     * 登录页中的验证码获取
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    /**
     * 不管是普通用户还是admin 登录后的首页中 都展示这个欢迎页面
     * @return
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 普通用户登录后 登录到普通用户的首页
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

    /**
     * admin登录后 登录到admin的首页
     * @return
     */
    @GetMapping("/adminIndex")
    public String adminIndex() {
        return "admin/adminIndex";
    }


    /**
     * admin用户的 借阅信息 页面跳转
     * @param model
     * @param request
     * @return
     */
    @GetMapping("adminBorrowList")
    public String adminBorrowList(Model model,HttpServletRequest request) {
        // 这个session中的属性是在登录的时候 设置在session中的
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList", categoryList);
        return "admin/borrowInfoList";
    }

    /**
     * admin用户的 书籍管理 页面跳转
     * @param model
     * @return
     */
    @GetMapping("adminBookList")
    public String adminBookList(Model model) {
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList", categoryList);
        return "admin/bookList";
    }

    /**
     * admin用户的 书籍类别管理 页面跳转
     * @return
     */
    @GetMapping("adminCategoryList")
    public String adminCategoryList() {
        return "admin/bookCategoryList";
    }


    /**
     * admin用户的 添加书籍 页面跳转
     * @param model
     * @return
     */
    @GetMapping("book/adminAddBook")
    public String adminAddBook(Model model) {
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList", categoryList);
        return "admin/bookAdd";
    }

    /**
     * admin用户的 书籍编辑 页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("book/adminEditBook/{id}")
    public String getBookById(@PathVariable Integer id, Model model) {
        Book book = bookService.getBooksById(id);
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("book", book);
        model.addAttribute("categoryList", categoryList);
        return "admin/bookEdit";
    }


    /**
     * admin用户的 添加书籍分类 页面跳转
     *
     * @param model
     * @return
     */
    @GetMapping("book/adminAddBookCategory")
    public String adminAddBookCategory(Model model) {
        return "admin/bookCategoryAdd";
    }

    /**
     * 编辑书籍类别的路由
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("book/adminEditBookCategory/{categoryId}")
    public String getBookCategoryById(@PathVariable Integer categoryId, Model model) {
        BookCategory bookCategory = bookService.getBookCategoryById(categoryId);
        model.addAttribute("bookCategory", bookCategory);
        return "admin/bookCategoryEdit";
    }

    /**
     * admin用户的 用户管理 页面跳转
     * @return
     */
    @GetMapping("adminUserList")
    public String adminUserList() {
        return "admin/userList";
    }

    /**
     * admin用户的 添加书籍分类 页面跳转
     *
     * @param model
     * @return
     */
    @GetMapping("user/adminAddUser")
    public String adminAddUser(Model model) {
        return "admin/userAdd";
    }

    /**
     * admin用户的 用户编辑 页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("user/adminEditUser/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        // 将密码设置为null 防止传到前端去
        user.setUserPassword(null);
        model.addAttribute("user", user);
        return "admin/userEdit";
    }

    /**
     * 普通用户的 书籍查询 页面跳转
     * @param model
     * @param request
     * @return
     */
    @GetMapping("userBookList")
    public String userBookList(Model model,HttpServletRequest request) {
        // 这个session中的属性是在登录的时候 设置在session中的
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList", categoryList);
        return "user/bookList";
    }


    /**
     * 普通用户的 图书借阅 页面跳转
     * @param model
     * @param request
     * @return
     */
    @GetMapping("userBorrowInfoList")
    public String userBorrowInfoList(Model model,HttpServletRequest request) {
        // 这个session中的属性是在登录的时候 设置在session中的
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        // 获取所有的书籍分类
        List<BookCategory> categoryList = bookService.getAllBookCategories();
        model.addAttribute("categoryList", categoryList);
        return "user/borrowInfoList";
    }
}
