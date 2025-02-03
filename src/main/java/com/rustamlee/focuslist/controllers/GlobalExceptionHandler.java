package com.rustamlee.focuslist.controllers;

import com.rustamlee.focuslist.domain.dto.ErrorResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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
