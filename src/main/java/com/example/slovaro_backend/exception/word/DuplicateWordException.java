package com.example.slovaro_backend.exception.word;

import com.example.slovaro_backend.exception.SlovaroException;

public class DuplicateWordException extends SlovaroException {
    public DuplicateWordException(Long sourceId) {
        super("Такое слово уже существует в источнике с id=" + sourceId + ".");
    }
}
