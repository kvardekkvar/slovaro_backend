package com.example.slovaro_backend.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SlovaroError> catchGeneral(RuntimeException exception) {
        SlovaroError errorContainer = new SlovaroError(500, exception.getClass().getName() + ": " + exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<SlovaroError> handleMissingParams(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        SlovaroError errorContainer = new SlovaroError(400, parameterName + " query parameter is missing from request.");
        return new ResponseEntity<>(errorContainer, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SlovaroError> catchSourceNotFoundException(SourceNotFoundException exception) {
        SlovaroError errorContainer = new SlovaroError(404, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SlovaroError> catchDuplicateSource(DuplicateSourceException exception) {
        SlovaroError errorContainer = new SlovaroError(400, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.BAD_REQUEST);
    }

}
