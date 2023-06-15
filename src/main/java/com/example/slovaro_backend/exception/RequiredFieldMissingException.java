package com.example.slovaro_backend.exception;

public class RequiredFieldMissingException extends RuntimeException {

    public RequiredFieldMissingException(String message){
        super(message);
    }
}
