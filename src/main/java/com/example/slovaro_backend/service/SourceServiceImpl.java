package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.SourceDAO;
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
    SourceDAO sourceDAO;

    @Override
    public List<Source> getSources() {
        return sourceDAO.showAll();
    }

    @Override
    public Source getSourceById(long id) {
        return sourceDAO.findById(id);
    }

    @Override
    public Source addSource(Source source){
        return sourceDAO.add(source);
    }
}
