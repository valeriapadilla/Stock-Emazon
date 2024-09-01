package com.example.stockemazon.infraestructure.exceptionsHandler;

public enum exceptionResponse {
   MESSAGE("Message");

    private String message;
    exceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
