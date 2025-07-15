package com.ethan.emsp.infrastructure.persistence.query.common;

import lombok.Getter;

@Getter
public class PageQuery {
    private Integer pageNum;
    private Integer pageSize;

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        this.pageSize = pageSize;
    }

// 可选：排序、过滤通用参数
}
