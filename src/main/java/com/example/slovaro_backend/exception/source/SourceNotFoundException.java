package com.example.slovaro_backend.exception.source;

import com.example.slovaro_backend.exception.SlovaroException;

public class SourceNotFoundException extends SlovaroException {

    public SourceNotFoundException(Long id){
        super("Источник с id = " + id +" не найден.");
    }

}
