package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Source;

import java.util.List;

public interface SourceDAO {

    Source findById(long id);

    List<Source> showAll();

    Source add(Source source);
}
