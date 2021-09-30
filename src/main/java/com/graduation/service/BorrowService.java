package com.graduation.service;

import com.graduation.domain.BorrowInfo;
import com.graduation.domain.BorrowInfoVo;
import com.graduation.domain.User;
import com.graduation.domain.param.BorrowParams;

import java.util.List;
/**
 * 借阅相关的service接口
 */
public interface BorrowService {
    /**
     * 借书
     * @param bookId
     * @param user
     * @return
     */
    BorrowInfo borrowBookByIdAndUsername(Integer bookId, User user);

    /**
     * 按照查询条件分页查询用户的借阅信息
     * @param borrowParams
     * @param username
     * @return
     */
    List<BorrowInfoVo> getBorrowLists(BorrowParams borrowParams, User username);

    /**
     * 按照查询条件 获取符合条件的所有用户的数据的总数
     * @param borrowParams
     * @param username
     * @return
     */
    Long getTotalCount(BorrowParams borrowParams, User username);

    /**
     * 还书
     * @param bookId
     * @param user
     */
    void returnBookById(Integer bookId, User user);

    /**
     * 按照用户id和书籍id查询这个人的这本书的借阅信息
     * @return
     */
    BorrowInfoVo selectBorrowInfoByBookIdAndUserId();
}
