package com.graduation.mapper;

import com.graduation.domain.BorrowInfo;
import com.graduation.domain.BorrowInfoVo;
import com.graduation.domain.User;
import com.graduation.domain.param.BorrowParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 借阅Mapper
 */
public interface BorrowMapper {
    // 借书
    void borrowBookByBorrowInfo(BorrowInfo borrowInfo);
    // 按照查询条件分页查询用户的借阅信息
    List<BorrowInfoVo> getBorrowLists(@Param("params") BorrowParams borrowParams, @Param("user") User username);
    // 按照查询条件 获取符合条件的所有用户的数据的总数
    Long getTotalCount(@Param("params") BorrowParams borrowParams,  @Param("user") User username);
    // 还书
    void returnBookById(@Param("bookId") Integer bookId, @Param("user") User user);
}
