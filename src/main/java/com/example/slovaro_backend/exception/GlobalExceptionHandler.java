package com.example.slovaro_backend.exception;

import com.example.slovaro_backend.exception.source.DuplicateSourceException;
import com.example.slovaro_backend.exception.source.SourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SlovaroErrorOutput> catchGeneral(RuntimeException exception) {
        SlovaroErrorOutput errorContainer = new SlovaroErrorOutput(500, exception.getClass().getSimpleName() + ": " + exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<SlovaroErrorOutput> handleMissingParams(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        SlovaroErrorOutput errorContainer = new SlovaroErrorOutput(400, parameterName + " query parameter is missing from request.");
        return new ResponseEntity<>(errorContainer, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SlovaroErrorOutput> catchSourceNotFoundException(SourceNotFoundException exception) {
        SlovaroErrorOutput errorContainer = new SlovaroErrorOutput(404, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SlovaroErrorOutput> catchDuplicateSource(DuplicateSourceException exception) {
        SlovaroErrorOutput errorContainer = new SlovaroErrorOutput(400, exception.getMessage());
        return new ResponseEntity<>(errorContainer, HttpStatus.BAD_REQUEST);
    }

}
