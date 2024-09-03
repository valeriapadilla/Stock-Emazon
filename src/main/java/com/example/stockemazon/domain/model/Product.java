package com.example.stockemazon.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private List<Category> categories;

    public Product(Long id, String name, String description, Long quantity, BigDecimal price, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}

