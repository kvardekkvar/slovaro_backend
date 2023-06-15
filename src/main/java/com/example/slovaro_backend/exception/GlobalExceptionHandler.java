package com.example.slovaro_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SlovaroError> catchGeneral(RuntimeException exception){
        SlovaroError errorContainer = new SlovaroError(500, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<SlovaroError> catchSourceNotFoundException(SourceNotFoundException exception) {
        SlovaroError errorContainer = new SlovaroError(404, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.NOT_FOUND);
    }
}
