package com.example.stockemazon.domain.util;

public class domainConstant {
    private domainConstant() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final String INVALID_CATEGORY_NAME_CHARACTERS_EXCEPTION_MESSAGE = "The name cannot be null and must have a maximum of 50 characters";
    public static final String INVALID_CATEGORY_DESCRIPTION_CHARACTERS_EXCEPTION_MESSAGE = "The description cannot be null and must have a maximum of 90 characters.";
    public static final String CATEGORY_ALREADY_EXIST_EXCEPTION = "Category name already exists";
}
