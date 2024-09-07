package com.example.stockemazon.domain.model;

import java.util.List;

public class PageCustom<T> {
    private List<T> content;
    private Integer totalPages;
    private long totalElements;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;

    public PageCustom(){

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

    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }

    public Boolean getHasNextPage() {return hasNextPage;}

    public void setHasNextPage(Boolean hasNextPage) {this.hasNextPage = hasNextPage;}

    public Boolean getHasPreviousPage() {return hasPreviousPage;}

    public void setHasPreviousPage(Boolean hasPreviousPage) {this.hasPreviousPage = hasPreviousPage;}

}
