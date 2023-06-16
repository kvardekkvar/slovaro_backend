package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.SourceDAO;
import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.exception.DatabaseException;
import com.example.slovaro_backend.exception.source.DuplicateSourceException;
import com.example.slovaro_backend.exception.source.SourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Source addSource(Source source) {
        if (sourceDAO.checkIfAlreadyExists(source)) {
            throw new DuplicateSourceException();
        }
        return sourceDAO.add(source).orElseThrow(() -> new DatabaseException("Database insert error. No rows were inserted"));
    }

    @Override
    public void updateSource(Source newSource) {
        Long sourceId = newSource.getId();
        Source oldSource = sourceDAO.findById(sourceId).orElseThrow(() -> new SourceNotFoundException(sourceId));

        if (!Objects.equals(newSource.getUserId(), oldSource.getUserId())) {
            throw new RuntimeException("Только имя источника подлежит изменению.");
        }

        sourceDAO.update(newSource);
    }

    @Override
    public void deleteSourceById(Long id) {
        if (sourceDAO.findById(id).isPresent()) {
            sourceDAO.deleteById(id);
        } else {
            throw new SourceNotFoundException(id);
        }
    }
}
