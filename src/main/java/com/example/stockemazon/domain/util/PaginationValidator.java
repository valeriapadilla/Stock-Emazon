package com.example.stockemazon.domain.util;

import com.example.stockemazon.domain.exceptions.PaginationException;

import java.util.Arrays;
import java.util.List;

public class PaginationValidator {
    private static final List<String> ALLOWED_SORT_DIRECTIONS = Arrays.asList("ASC", "DESC");
    private static final List<String> ALLOWED_SORT_BY_FIELDS = Arrays.asList("name", "description");

    public static void validatePaginationParameters(int page, int size, String sort, String sortBy) {
        if (page < 0) {
            throw new PaginationException("Page index cannot be negative.");
        }

        if (size < 0) {
            throw new PaginationException("Page size cannot be negative.");
        }

        if (sort == null || sort.trim().isEmpty()) {
            throw new PaginationException("Sort field cannot be null or empty.");
        }

        if (!ALLOWED_SORT_DIRECTIONS.contains(sort.toUpperCase())) {
            throw new PaginationException("Invalid sort direction. Allowed values are 'ASC' or 'DESC'.");
        }

        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new PaginationException("SortBy field cannot be null or empty.");
        }

        if (!ALLOWED_SORT_BY_FIELDS.contains(sortBy.toLowerCase())) {
            throw new PaginationException("Invalid sortBy field. Allowed values are 'name' or 'description'.");
        }
    }

}
