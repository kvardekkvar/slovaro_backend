package com.example.slovaro_backend.service;

import com.example.slovaro_backend.entity.Source;

import java.util.List;

public interface SourceService {

    List<Source> getSources();

    Source getSourceById(long id);
}
