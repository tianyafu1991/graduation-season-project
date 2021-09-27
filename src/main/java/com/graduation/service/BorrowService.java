package com.graduation.service;

import com.graduation.domain.BorrowInfo;
import com.graduation.domain.User;

public interface BorrowService {
    /**
     * 借书
     * @param bookId
     * @param user
     * @return
     */
    BorrowInfo borrowBookByIdAndUsername(Integer bookId, User user);
}
