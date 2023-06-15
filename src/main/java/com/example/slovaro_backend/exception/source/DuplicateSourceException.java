package com.example.slovaro_backend.exception.source;

import com.example.slovaro_backend.exception.SlovaroException;
import org.springframework.web.bind.annotation.ResponseBody;

public class DuplicateSourceException extends SlovaroException {


    public DuplicateSourceException(){
        super("Такой источник уже существует.");
    }
}
