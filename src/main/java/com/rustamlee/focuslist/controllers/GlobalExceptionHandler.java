package com.rustamlee.focuslist.controllers;

import com.rustamlee.focuslist.domain.dto.ErrorResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler to catch and handle exceptions across the entire application.
 * It centralizes exception management and customizes error responses.
 * When an exception occurs in the application's controllers, Spring
 * looks for a method with a matching @ExceptionHandler annotation
 * inside the class marked with @ControllerAdvice and passes the
 * exception there for handling.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponce> handleExceptions(
            RuntimeException ex, WebRequest request) {
        ErrorResponce errorResponce = new ErrorResponce(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
     return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
    }
}