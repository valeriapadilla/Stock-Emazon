package com.example.stockemazon.application.dto;

import com.example.stockemazon.application.util.ApplicationConstant;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {
    @NotBlank(message = ApplicationConstant.PRODUCT_NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = ApplicationConstant.PRODUCT_DESCRIPTION_NOT_BLANK)
    private String description;

    @NotNull(message = ApplicationConstant.PRODUCT_QUANTITY_NOT_NULL)
    @Min(value = ApplicationConstant.PRODUCT_MIN_QUANTITY, message = ApplicationConstant.PRODUCT_QUANTITY_MIN_VALUE)
    private Long quantity;

    @NotNull(message = ApplicationConstant.PRODUCT_PRICE_NOT_NULL)
    @DecimalMin(value = ApplicationConstant.PRODUCT_PRICE_MIN_VALUE, message = ApplicationConstant.PRODUCT_PRICE_MIN_VALUE)
    private BigDecimal price;

    @NotNull(message = ApplicationConstant.PRODUCT_BRAND_NOT_NULL)
    private Long brandId;

    @NotNull(message = ApplicationConstant.PRODUCT_CATEGORIES_NOT_NULL)
    @Size(min = ApplicationConstant.PRODUCT_MIN_CATEGORIES, max = ApplicationConstant.PRODUCT_MAX_CATEGORIES, message = ApplicationConstant.PRODUCT_CATEGORIES_SIZE)
    private List<Long> categoriesId;

    public ProductRequest(String name, String description, Long quantity, BigDecimal price, Long brandId, List<Long> categoriesId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
        this.categoriesId = categoriesId;
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

    public List<Long> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<Long> categories) {
        this.categoriesId = categories;
    }
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
