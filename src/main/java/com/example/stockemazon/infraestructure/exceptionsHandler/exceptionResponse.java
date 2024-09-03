package com.example.stockemazon.infraestructure.exceptionsHandler;

public enum exceptionResponse {
   MESSAGE("Message"),
   CATEGORY_ALREADY_EXITS("Category name already exits"),
    BRAND_ALREADY_EXITS("Brand name already exits");

    private String message;
    exceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
