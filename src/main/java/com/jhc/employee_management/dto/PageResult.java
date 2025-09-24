package com.jhc.employee_management.dto;
import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {
    private List<T> records;   // 当前页的数据
    private long total;        // 总条数
    private int page;          // 当前页码
    private int size;          // 每页大小
    private int totalPages;    // 总页数

    public PageResult(List<T> records, int page, int size, long total) {
        this.records = records;
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPages = (int) Math.ceil((double) total / size);
    }
}
