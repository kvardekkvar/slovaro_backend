package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.SourceDAO;
import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.exception.DatabaseException;
import com.example.slovaro_backend.exception.source.DuplicateSourceException;
import com.example.slovaro_backend.exception.source.SourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Source getSourceById(Long id) {
        return sourceDAO.findById(id).orElseThrow(() -> new SourceNotFoundException(id));
    }

    @Override
    public Source addSource(Source source){
        if (sourceDAO.checkIfAlreadyExists(source)) {
            throw new DuplicateSourceException();
        }
        return sourceDAO.add(source).orElseThrow(() -> new DatabaseException("Database insert error. No rows were inserted"));
    }
}
