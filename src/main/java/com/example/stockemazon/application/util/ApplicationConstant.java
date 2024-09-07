package com.example.stockemazon.application.util;

import java.math.BigDecimal;

public class ApplicationConstant {
    private ApplicationConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final int CATEGORY_MIN_CHARACTERS_NAME = 1;
    public static final int CATEGORY_MAX_CHARACTERS_NAME = 50;
    public static final int CATEGORY_MIN_CHARACTERS_DESCRIPTION = 1;
    public static final int CATEGORY_MAX_CHARACTERS_DESCRIPTION = 90;

    public static final String CATEGORY_NAME_NULL_EXCEPTION = "Category's name is null";
    public static final String CATEGORY_DESCRIPTION_NULL_EXCEPTION = "Category's description is null";
    public static final String CATEGORY_DESCRIPTION_SIZE_EXCEPTION = "Category's description out of length";
    public static final String CATEGORY_NAME_SIZE_EXCEPTION = "Category's name out of length";


    public static final int BRAND_MIN_CHARACTERS_NAME = 1;
    public static final int BRAND_MAX_CHARACTERS_NAME = 50;
    public static final int BRAND_MIN_CHARACTERS_DESCRIPTION = 1;
    public static final int BRAND_MAX_CHARACTERS_DESCRIPTION = 120;

    public static final String BRAND_NAME_NULL_EXCEPTION = "Brand's name is null";
    public static final String BRAND_DESCRIPTION_NULL_EXCEPTION = "Brand's description is null";
    public static final String BRAND_DESCRIPTION_SIZE_EXCEPTION = "Brand's description out of length";
    public static final String BRAND_NAME_SIZE_EXCEPTION = "Brand's name out of length";

    public static final String PRODUCT_NAME_NOT_BLANK = "Product name cannot be blank";
    public static final String PRODUCT_DESCRIPTION_NOT_BLANK = "Product description cannot be blank";
    public static final String PRODUCT_QUANTITY_NOT_NULL = "Product quantity cannot be null";
    public static final String PRODUCT_QUANTITY_MIN_VALUE = "Product quantity cannot be less than 0";
    public static final String PRODUCT_PRICE_NOT_NULL = "Product price cannot be null";
    public static final String PRODUCT_PRICE_MIN_VALUE = "Product price cannot be less than 0";
    public static final String PRODUCT_BRAND_NOT_NULL = "Product brand cannot be null";
    public static final String PRODUCT_CATEGORIES_NOT_NULL = "Product categories list cannot be null";
    public static final String PRODUCT_CATEGORIES_SIZE = "There must be between 1 and 3 product categories";

    // Constants for numeric values
    public static final int PRODUCT_MIN_QUANTITY = 0;
    public static final BigDecimal PRODUCT_MIN_PRICE =BigDecimal.ZERO;
    public static final int PRODUCT_MIN_CATEGORIES = 1;
    public static final int PRODUCT_MAX_CATEGORIES = 3;
}
