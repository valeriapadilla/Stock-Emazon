package com.example.stockemazon.application.util;

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

}
