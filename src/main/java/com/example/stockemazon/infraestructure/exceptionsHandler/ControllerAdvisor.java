package com.example.stockemazon.infraestructure.exceptionsHandler;

import com.example.stockemazon.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ControllerAdvisor {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), categoryAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), categoryNotFoundException.getMessage()));
    }

    @ExceptionHandler(EmptyAttributeException.class)
    public ResponseEntity<Map<String, String>> handleEmptyAttributeException(
            EmptyAttributeException emptyAttributeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), emptyAttributeException.getMessage()));
    }

    @ExceptionHandler(DataOutOfLenghtException.class)
    public ResponseEntity<Map<String, String>> handleDataConstraintViolationException(
            DataOutOfLenghtException dataConstraintViolationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), dataConstraintViolationException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), errors));
    }
    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<Map<String, String>> handleMissingAttributeException(
            MissingAttributeException ex) {
        Map<String, String> errorResponse = Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PaginationException.class)
    public ResponseEntity<Map<String, String>> handlePaginationException(
            PaginationException ex) {
        Map<String, String> errorResponse= Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandAlreadyExistsException brandAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), brandAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFoundException(
            BrandNotFoundException brandNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(exceptionResponse.MESSAGE.getMessage(), brandNotFoundException.getMessage()));
    }
}
