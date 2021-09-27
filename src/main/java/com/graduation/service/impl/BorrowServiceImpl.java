package com.graduation.service.impl;

import com.graduation.domain.BorrowInfo;
import com.graduation.domain.User;
import com.graduation.mapper.BorrowMapper;
import com.graduation.service.BorrowService;
import com.graduation.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    public static final Long BORROW_MONTH=3L;

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
