package com.graduation.mapper;

import com.graduation.domain.BorrowInfo;

public interface BorrowMapper {
    // 借书
    void borrowBookByBorrowInfo(BorrowInfo borrowInfo);
}
