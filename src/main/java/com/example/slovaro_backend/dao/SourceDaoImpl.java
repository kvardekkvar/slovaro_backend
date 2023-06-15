package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Source;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SourceDaoImpl implements SourceDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Source> findById(long id) {

        try (Session session = sessionFactory.openSession()) {
            Source source = session.get(Source.class, id);
            return Optional.ofNullable(source);
        }
    }

    @Override
    public List<Source> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Source", Source.class).getResultList();
        }
    }

    @Override
    public Optional<Source> add(Source source) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(source);
            return Optional.ofNullable(source);
        }
    }
}
