package com.graduation.service.impl;

import com.graduation.domain.BorrowInfo;
import com.graduation.domain.BorrowInfoVo;
import com.graduation.domain.User;
import com.graduation.domain.param.BorrowParams;
import com.graduation.mapper.BorrowMapper;
import com.graduation.service.BorrowService;
import com.graduation.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    public static final Long BORROW_MONTH=3L;


    /**
     * 还书
     *
     * @param bookId
     * @param user
     */
    @Override
    public void returnBookById(Integer bookId, User user) {
        borrowMapper.returnBookById(bookId, user);
    }

    /**
     * 按照查询条件分页查询用户的借阅信息
     *
     * @param borrowParams
     * @param username
     * @return
     */
    @Override
    public List<BorrowInfoVo> getBorrowLists(BorrowParams borrowParams, User username) {
        return borrowMapper.getBorrowLists(borrowParams, username);
    }

    /**
     * 按照查询条件 获取符合条件的所有用户的数据的总数
     *
     * @param borrowParams
     * @param username
     * @return
     */
    @Override
    public Long getTotalCount(BorrowParams borrowParams, User username) {
        return borrowMapper.getTotalCount(borrowParams, username);
    }

    /**
     * 借书
     *
     * @param bookId 书籍id
     * @param user   借书的用户对象
     * @return
     */
    @Override
    public BorrowInfo borrowBookByIdAndUsername(Integer bookId, User user) {
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBookId(bookId);
        borrowInfo.setUserId(user.getId());
        String borrowTime = DateUtils.getCurrentLocalDateTime();
        borrowInfo.setBorrowTime(borrowTime); // 借书时间
        borrowInfo.setReturnTime(DateUtils.getReturnTime(borrowTime,BORROW_MONTH)); // 需要还书的时间 默认借书3个月
        borrowInfo.setRealReturnTime(null);
        borrowInfo.setReturnFlg("0");
        borrowMapper.borrowBookByBorrowInfo(borrowInfo);
        return borrowInfo;
    }
}
