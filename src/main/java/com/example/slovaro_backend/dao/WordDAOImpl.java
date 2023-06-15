package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class WordDAOImpl implements WordDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Word> showAllBySourceId(long sourceId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Word where sourceId = :sourceId", Word.class)
                    .setParameter("sourceId", sourceId)
                    .getResultList();
        }
    }

    @Override
    public Optional<Word> add(Word word) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(word);
            transaction.commit();
        }

        if (word.getId() == null) {
            return Optional.empty();
        } else {
            return Optional.of(word);
        }
    }

    @Override
    public boolean checkIfAlreadyExists(Word word) {
        try (Session session = sessionFactory.openSession()) {
            Query<Source> query = session.createQuery("From Word where userId=:userid and content=:content and sourceId=:sourceid", Source.class)
                    .setParameter("userid", word.getUserId())
                    .setParameter("sourceid", word.getSourceId())
                    .setParameter("content", word.getContent());
            List<Source> results = query.getResultList();
            return results.size() > 0;
        }

    }
}
