package com.example.slovaro_backend;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.Word;
import com.example.slovaro_backend.service.SourceService;
import com.example.slovaro_backend.service.SourceServiceImpl;
import com.example.slovaro_backend.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getStatus(){
        return "Ololo, I'm successful cucumber!";
    }

    @GetMapping("/sources")
    public List<Source> getSources(){
        return sourceService.getSources();
    }
    @GetMapping("/source")
    public Source getSources(@RequestParam int id){
        return sourceService.getSourceById(id);
    }

    @GetMapping("/words")
    public List<Word> getWords(@RequestParam int sourceId){
        Source source = sourceService.getSourceById(sourceId);
        return wordService.getWords(source);
    }

}
