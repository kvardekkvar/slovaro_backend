package com.example.slovaro_backend.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message){
        super(message);
    }
}
