package com.graduation.controller;


import com.graduation.common.CommonResponse;
import com.graduation.domain.Book;
import com.graduation.domain.BorrowInfo;
import com.graduation.domain.User;
import com.graduation.service.BorrowService;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
