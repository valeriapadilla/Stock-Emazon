package com.example.stockemazon.domain.util;

import com.example.stockemazon.domain.exceptions.PaginationException;

import java.util.Arrays;
import java.util.List;

public class PaginationValidator {
    private static final List<String> ALLOWED_SORT_DIRECTIONS = Arrays.asList(DomainConstant.ASC, DomainConstant.DESC);
    private static final List<String> ALLOWED_SORT_BY_FIELDS = Arrays.asList(DomainConstant.NAME, DomainConstant.DESCRIPTION);

    public static void validatePaginationParameters(int page, int size, String sort, String sortBy) {
        if (page < 0) {
            throw new PaginationException(DomainConstant.PAGE_INDEX_NEGATIVE_ERROR);
        }

        if (size < 0) {
            throw new PaginationException(DomainConstant.PAGE_SIZE_NEGATIVE_ERROR);
        }

        if (sort == null || sort.trim().isEmpty()) {
            throw new PaginationException(DomainConstant.SORT_FIELD_NULL_OR_EMPTY_ERROR);
        }

        if (!ALLOWED_SORT_DIRECTIONS.contains(sort.toUpperCase())) {
            throw new PaginationException(DomainConstant.INVALID_SORT_DIRECTION_ERROR);
        }

        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new PaginationException(DomainConstant.SORT_BY_FIELD_NULL_OR_EMPTY_ERROR);
        }

        if (!ALLOWED_SORT_BY_FIELDS.contains(sortBy.toLowerCase())) {
            throw new PaginationException(DomainConstant.INVALID_SORT_BY_FIELD_ERROR);
        }
    }

}
