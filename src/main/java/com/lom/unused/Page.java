package com.lom.unused;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Page<T> {

    private List<T> data;
    private int pageSize;
    private int currentPage;
    private int count;

    private int getTotalPages() {
        return count / pageSize;
    }

    public Pagination getPagination() {
        return Pagination.builder().count(count).currentPage(currentPage).pageSize(pageSize).totalPages(getTotalPages()).build();
    }
}
