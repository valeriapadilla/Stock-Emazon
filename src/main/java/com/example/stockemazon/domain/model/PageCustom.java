package com.example.stockemazon.domain.model;

import java.util.List;

public class PageCustom<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;

    public PageCustom(List<T> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getContent()
    {
         return content;
    }

    public void setContent(List<T> content)
    {
        this.content = content;
    }

    public long getTotalElements()
    {
        return totalElements;
    }

    public void setTotalElements(long totalElements)
    {
        this.totalElements = totalElements;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int pageSize)
    {
        this.totalPages = totalPages;
    }

}
