package com.example.stockemazon.domain.exceptions;

public class MissingAttributeException extends RuntimeException {
    public MissingAttributeException(String message) {
        super(message);
    }
}
