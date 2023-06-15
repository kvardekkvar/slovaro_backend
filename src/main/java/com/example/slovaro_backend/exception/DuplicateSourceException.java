package com.example.slovaro_backend.exception;

public class DuplicateSourceException extends RuntimeException{

    public DuplicateSourceException(String message){
        super(message);
    }
}
