package com.ethan.emsp.infrastructure.persistence.query.common;

import com.github.pagehelper.PageInfo;
import lombok.Getter;

import java.util.*;


@Getter
public class PageResult<T> {
    private final long total;
    private final int pages;
    private final int pageNum;
    private final int pageSize;
    private final List<T> records;
    private final boolean hasNext;
    private final boolean hasPrevious;

    public PageResult(List<T> records, PageInfo<?> pageInfo) {
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.records = records;
        this.hasNext = pageInfo.isHasNextPage();
        this.hasPrevious = pageInfo.isHasPreviousPage();
    }

    public PageResult(long total, int pages, int pageNum, int pageSize, List<T> list, boolean hasNext, boolean hasPrevious) {
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.records = new ArrayList<>(list);
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public static <T> PageResult<T> of(List<T> records, PageInfo<?> pageInfo) {
        return new PageResult<>(records, pageInfo);
    }

    public List<T> getRecords() {
        return Collections.unmodifiableList(records);
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }
}
