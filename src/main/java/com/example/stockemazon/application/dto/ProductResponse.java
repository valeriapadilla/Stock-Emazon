package com.example.stockemazon.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private BrandResponse brand;
    private List<CategoryProductResponse> categories;

    public ProductResponse(Long id, String name, String description, Integer quantity, BigDecimal price, BrandResponse brand, List<CategoryProductResponse> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }
    public ProductResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BrandResponse getBrand() {
        return brand;
    }

    public void setBrand(BrandResponse brand) {
        this.brand = brand;
    }

    public List<CategoryProductResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductResponse> categories) {this.categories = categories;}
}
