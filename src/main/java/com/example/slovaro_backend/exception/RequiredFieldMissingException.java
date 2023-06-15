package com.example.slovaro_backend.exception;

public class RequiredFieldMissingException extends SlovaroException {

    public RequiredFieldMissingException(String message){
        super(message);
    }
}
