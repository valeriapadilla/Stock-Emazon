package com.example.stockemazon.domain.exceptions;

public class InvalidCategoryDescriptionException extends RuntimeException {

    public InvalidCategoryDescriptionException(String message) {
        super(message);
    }
}
