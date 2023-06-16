package com.example.slovaro_backend.dao;

import com.example.slovaro_backend.entity.Word;

import java.util.List;
import java.util.Optional;

public interface WordDAO {

    List<Word> showAllBySourceId(long sourceId);

    Optional<Word> add(Word word);

    boolean checkIfAlreadyExists(Word word);

    Optional<Word> findById(Long id);

    void update(Word newWord);

    void deleteById(Long id);
}
