package com.example.stockemazon.domain.exceptions;

public class TooManyCategoriesException extends RuntimeException {
    public TooManyCategoriesException(String message) {
        super(message);
    }
}