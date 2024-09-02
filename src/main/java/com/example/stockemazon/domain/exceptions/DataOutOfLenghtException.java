package com.example.stockemazon.domain.exceptions;

public class DataOutOfLenghtException extends RuntimeException {
    public DataOutOfLenghtException(String message) {
        super(message);
    }
}
