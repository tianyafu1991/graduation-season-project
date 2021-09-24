package com.graduation.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {

    private Integer page;

    private Integer limit;

    /**
     * 获取分页的开始
     * @return
     */
    public Integer getStart() {
        return (page - 1) * limit;
    }
}
