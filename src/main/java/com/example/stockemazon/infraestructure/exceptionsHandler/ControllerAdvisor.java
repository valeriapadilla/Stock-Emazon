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

@ControllerAdvice
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

    @ExceptionHandler(CategoryDataOutOfLenghtException.class)
    public ResponseEntity<Map<String, String>> handleDataConstraintViolationException(
            CategoryDataOutOfLenghtException dataConstraintViolationException) {
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
        return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
    }
    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<Map<String, String>> handleMissingAttributeException(
            MissingAttributeException ex) {
        Map<String, String> errorResponse = Collections.singletonMap("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
