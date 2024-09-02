package com.example.stockemazon.domain.util;

public class DomainConstant {
    private DomainConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description"
            ;
    public static final String CATEGORY_NOTFOUND_EXCEPTION = "The category does not exist";
    public static final String CATEGORY_MISSING_ATTRIBUTE_EXCEPTION = "Some Values of the category are empty or null";
    public static final String CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION = "Values of the category out of lenght";


    public static final String PAGE_INDEX_NEGATIVE_ERROR = "Page index cannot be negative.";
    public static final String PAGE_SIZE_NEGATIVE_ERROR = "Page size cannot be negative.";
    public static final String SORT_FIELD_NULL_OR_EMPTY_ERROR = "Sort field cannot be null or empty.";
    public static final String INVALID_SORT_DIRECTION_ERROR = "Invalid sort direction. Allowed values are 'ASC' or 'DESC'.";
    public static final String SORT_BY_FIELD_NULL_OR_EMPTY_ERROR = "SortBy field cannot be null or empty.";
    public static final String INVALID_SORT_BY_FIELD_ERROR = "Invalid sortBy field. Allowed values are 'name' or 'description'.";

    public static final String BRAND_MISSING_ATTRIBUTE_EXCEPTION = "Some Values of Brand are empty or null";
    public static final String BRAND_DATA_OUT_OF_LENGHT_EXCEPTION = "Values of brand out of lenght";
    public static final String BRAND_NOT_FOUND_EXCEPTION = "The brand does not exist";




}
