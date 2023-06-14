package com.example.slovaro_backend.service;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    Environment env;

    @Autowired
    SessionFactory factory;
    public WordServiceImpl(Environment env) {
        this.env = env;
    }

    public List<Word> getWords(Source source) {

        try (Session session = factory.openSession()) {
            return session.createQuery("from Word where sourceId = :sourceId", Word.class)
                    .setParameter("sourceId", source.getId())
                    .getResultList();
        }
    }
}
