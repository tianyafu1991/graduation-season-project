package com.graduation.controller;


import cn.hutool.core.util.StrUtil;
import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.BorrowInfo;
import com.graduation.domain.BorrowInfoVo;
import com.graduation.domain.User;
import com.graduation.domain.param.BookParams;
import com.graduation.domain.param.BorrowParams;
import com.graduation.service.BorrowService;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private UserService userService;


    /**
     * 借书
     * @param bookId
     * @param username
     * @return
     */
    @PutMapping("/borrowBookById/{bookId}/{username}")
    @ResponseBody
    public CommonResponse<BorrowInfo> borrowBookById(@PathVariable Integer bookId, @PathVariable String username){
        User user = userService.getUserByUserName(username);
        System.out.println("bookId:" + bookId + " username: "+ username);
        BorrowInfo borrowInfo = borrowService.borrowBookByIdAndUsername(bookId,user);
        return CommonResponse.successInstance("借书成功");
    }



    /**
     * 按照条件获取该用户的借阅信息
     * @param borrowParams
     * @param username
     * @return
     */
    @GetMapping("/getBorrowLists/{username}")
    @ResponseBody
    public CommonResponse<List<BorrowInfoVo>> getBorrowLists(BorrowParams borrowParams, @PathVariable String username){
        // 必要的校验
        if(StrUtil.isBlank(username)){
            return CommonResponse.errorInstance("用户名传参错误");
        }
        // 验证用户名是否在库中存在
        User user = userService.getUserByUserName(username);
        if(null == user){
            return CommonResponse.errorInstance("用户名传参错误");
        }
        // 按照查询条件分页查询用户的借阅信息
        // 根据用户名查询用户 如果是管理员 是可以查看所有用户的借阅信息的  如果是普通用户 则只能查看自己的借阅信息
        List<BorrowInfoVo> borrowInfoVos = borrowService.getBorrowLists(borrowParams,user);
        // 按照查询条件 获取符合条件的所有用户的数据的总数
        Long totalCount = borrowService.getTotalCount(borrowParams,user);
        CommonResponse response = CommonResponse.successInstance(borrowInfoVos, totalCount);
        return response;
    }

    /**
     * 借书
     * @param bookId
     * @param username
     * @return
     */
    @PutMapping("/returnBookById/{bookId}/{username}")
    @ResponseBody
    public CommonResponse<BorrowInfo> returnBookById(@PathVariable Integer bookId, @PathVariable String username){
        User user = userService.getUserByUserName(username);
        System.out.println("bookId:" + bookId + " username: "+ username);
        borrowService.returnBookById(bookId,user);
        return CommonResponse.successInstance("还书成功");
    }
}
