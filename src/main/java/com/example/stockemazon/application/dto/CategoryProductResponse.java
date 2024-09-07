package com.example.stockemazon.application.dto;

public class CategoryProductResponse {
    private Long id;
    private String name;
    public CategoryProductResponse() {}

    public CategoryProductResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
