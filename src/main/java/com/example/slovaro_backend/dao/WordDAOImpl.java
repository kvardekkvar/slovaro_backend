package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class WordDAOImpl implements WordDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Word> findBySourceId(long sourceId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Word where sourceId = :sourceId", Word.class)
                    .setParameter("sourceId", sourceId)
                    .getResultList();
        }
    }
}
