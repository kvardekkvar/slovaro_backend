package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.WordDAO;
import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordDAO wordDAO;

    public List<Word> getWords(Source source) {

        long sourceId = source.getId();
        return wordDAO.findBySourceId(sourceId);
    }
}

