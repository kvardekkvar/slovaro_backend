package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Word;

import java.util.List;

public interface WordDAO {

    List<Word> findBySourceId(long sourceId);

}
