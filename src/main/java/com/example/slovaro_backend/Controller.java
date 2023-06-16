package com.example.slovaro_backend;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import com.example.slovaro_backend.exception.RequiredFieldMissingException;
import com.example.slovaro_backend.service.SourceService;
import com.example.slovaro_backend.service.SourceServiceImpl;
import com.example.slovaro_backend.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/slovaro")
public class Controller {
    @Autowired
    private SourceService sourceService;
    @Autowired
    private WordService wordService;

    @GetMapping("/ololo")
    public String getStatus() {
        return "Ololo, I'm successful cucumber!";
    }

    @GetMapping("/sources")
    public List<Source> getSources() {
        return sourceService.getSources();
    }

    @GetMapping("/sources/{id}")
    public Source getSources(@PathVariable Long id) {
        return sourceService.getSourceById(id);
    }

    @PostMapping("/sources")
    public ResponseEntity<Source> addSource(@RequestBody Source source) {
        if (source != null && (source.getUserId() != null) && (source.getName() != null) && !source.getName().isBlank()) {
            return new ResponseEntity<>(sourceService.addSource(source), HttpStatus.CREATED);
        } else {
            throw new RequiredFieldMissingException("Required fields missing");
        }
    }

    @PutMapping("/sources/{id}")
    public ResponseEntity<Object> changeSourceName(@PathVariable long id, @RequestBody Source source) {
        assert id == source.getId();

        if (source != null && source.getId() != null && source.getUserId() != null && source.getName() != null && !source.getName().isBlank()) {
            sourceService.updateSource(source);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw new RequiredFieldMissingException("Required fields missing");
        }
    }

    @DeleteMapping("/sources/{id}")
    public ResponseEntity<Object> deleteSource(@PathVariable long id) {
        sourceService.deleteSourceById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/words")
    public List<Word> getWords(@RequestParam Long sourceId) {
        Source source = sourceService.getSourceById(sourceId);
        return wordService.getWords(source);
    }

    @PostMapping("/words")
    public ResponseEntity<Word> addWord(@RequestBody Word word) {
        if (word != null && word.getContent() != null && word.getUserId() != null && word.getSourceId() != null) {
            return new ResponseEntity<>(wordService.addWord(word), HttpStatus.CREATED);
        } else {
            throw new RequiredFieldMissingException("Required fields missing");
        }
    }

    @PutMapping("/words/{id}")
    public ResponseEntity<Object> updateWord(@PathVariable long id, @RequestBody Word word) {
        assert id == word.getId();

        if (word != null && word.getContent() != null && word.getUserId() != null && word.getSourceId() != null) {
            wordService.updateWord(word);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw new RequiredFieldMissingException("Required fields missing");
        }

    }

    @DeleteMapping("/words/{id}")
    public ResponseEntity<Object> deleteWord(@PathVariable long id){
        wordService.deleteWordById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}


