package com.example.slovaro_backend.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;

public class SlovaroError {

    private int code;
    private String message;


    public SlovaroError(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
