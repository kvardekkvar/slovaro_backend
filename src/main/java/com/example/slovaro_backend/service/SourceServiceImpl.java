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
    Environment env;

    public SourceServiceImpl(Environment env){
        this.env = env;
    }
    @Override
    public List<Source> getSources() {
        String password = env.getProperty("hibernate.connection.password");
        String user = env.getProperty("hibernate.connection.user");

        assert user != null;
        assert password != null;

        SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.user", user)
                .setProperty("hibernate.connection.password", password)
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Source.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        return session.createQuery("from Source", Source.class).getResultList();
    }
}
