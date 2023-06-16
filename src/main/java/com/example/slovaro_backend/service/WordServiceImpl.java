package com.example.slovaro_backend.service;

import com.example.slovaro_backend.dao.WordDAO;
import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import com.example.slovaro_backend.exception.DatabaseException;
import com.example.slovaro_backend.exception.word.DuplicateWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordDAO wordDAO;

    @Autowired
    SourceService sourceService;

    public List<Word> getWords(Source source) {

        long sourceId = source.getId();
        return wordDAO.showAllBySourceId(sourceId);
    }

    @Override
    public Word addWord(Word word) {
        Long sourceId = word.getSourceId();
        sourceService.getSourceById(sourceId);
        if (wordDAO.checkIfAlreadyExists(word)) {
            throw new DuplicateWordException(sourceId);
        }
        return wordDAO.add(word).orElseThrow(() -> new DatabaseException("Ошибка базы данных. Слово не было добавлено."));
    }

    @Override
    public void updateWord(Word newWord) {
        Long sourceId = newWord.getSourceId();
        Long wordId = newWord.getId();
        sourceService.getSourceById(sourceId);
        Word oldWord = wordDAO.findById(wordId).orElseThrow(() -> new RuntimeException("Слово не найдено."));

        if (!Objects.equals(oldWord.getUserId(), newWord.getUserId()) || !Objects.equals(oldWord.getSourceId(), newWord.getSourceId()) )
        {
            throw new RuntimeException("Только содержание слова и определение слова можно изменять.");
        } else{
            wordDAO.update(newWord);
        }



    }

    @Override
    public void deleteWordById(Long id) {
        if(wordDAO.findById(id).isPresent()){
            wordDAO.deleteById(id);
        } else {
            throw new RuntimeException("Слово не найдено.");
        }
    }
}

