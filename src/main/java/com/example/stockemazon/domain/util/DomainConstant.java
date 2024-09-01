package com.example.stockemazon.domain.util;

public class DomainConstant {
    private DomainConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_ALREADY_EXIST_EXCEPTION = "Category name already exists";
    public static final String CATEGORY_NOTFOUND_EXCEPTION = "The category does not exist";
    public static final String CATEGORY_MISSING_ATTRIBUTE_EXCEPTION = "Some Values are empty or null";
    public static final String CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION = "Values out of lenght";

}
