package com.example.stockemazon.domain.exceptions;

public class PaginationException extends RuntimeException {

    public PaginationException(String message) {
        super(message);
    }
}