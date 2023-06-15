package com.example.slovaro_backend.service;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;

import java.util.List;


public interface WordService {

    List<Word> getWords(Source source);

    Word addWord(Word word);
}
