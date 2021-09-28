package com.graduation.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页  layuimini的分页功能会传参 page 和 limit字段  所以分页查询的Params都需要继承该类
 */
@Data
public class Page implements Serializable {
    // 第几页
    private Integer page;
    // 每页多少条
    private Integer limit;

    /**
     * 获取分页的开始
     * @return
     */
    public Integer getStart() {
        return (page - 1) * limit;
    }
}
