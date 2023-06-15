package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Source;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
            Transaction transaction = session.beginTransaction();
            session.persist(source);
            transaction.commit();
        }
        if (source.getId() == null) {
            return Optional.empty();
        } else {
            return Optional.of(source);
        }
    }


    @Override
    public boolean checkIfAlreadyExists(Source source) {
        try (Session session = sessionFactory.openSession()) {
            Query<Source> query = session.createQuery("From Source where userId=:userid and name=:name", Source.class)
                    .setParameter("userid", source.getUserId())
                    .setParameter("name", source.getName());
            List<Source> results = query.getResultList();
            return results.size() > 0;
        }
    }
}
