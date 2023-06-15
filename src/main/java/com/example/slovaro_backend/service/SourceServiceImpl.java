package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.SourceDAO;
import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.User;
import com.example.slovaro_backend.exception.DatabaseException;
import com.example.slovaro_backend.exception.SourceNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceDAO sourceDAO;

    @Override
    public List<Source> getSources() {
        return sourceDAO.showAll();
    }

    @Override
    public Source getSourceById(long id) {
        return sourceDAO.findById(id).orElseThrow(() -> new SourceNotFoundException("Source with id " + id + " is not found." ));
    }

    @Override
    public Source addSource(Source source){
        return sourceDAO.add(source).orElseThrow(() -> new DatabaseException("Database insert error. No rows were inserted"));
    }
}
