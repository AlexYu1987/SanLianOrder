package com.petabytes.webussiness.slorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderPage<T> {
    @JsonProperty("totalelements")
    private long totalElements;

    @JsonProperty("totalpage")
    private int totalPage;

    @JsonProperty("currentpage")
    private int currentPage;

    @JsonProperty("datacount")
    private int pageSize;

    List<T> data;

    public OrderPage(long totalElements, int totalPage, int currentPage, int pageSize, List<T> data) {
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
