package com.example.slovaro_backend.service;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SessionFactory factory;


    @Override
    public List<Source> getSources() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Source", Source.class).getResultList();
        }
    }

    @Override
    public Source getSourceById(int id) {

        try (Session session = factory.openSession()) {
            return session.get(Source.class, id);
        }
    }
}
