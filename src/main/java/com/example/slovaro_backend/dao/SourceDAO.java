package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Source;

import java.util.List;
import java.util.Optional;

public interface SourceDAO {

    Optional<Source> findById(long id);

    List<Source> showAll();

    Optional<Source> add(Source source);

    boolean checkIfAlreadyExists(Source source);
}
