package com.example.slovaro_backend;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.service.SourceService;
import com.example.slovaro_backend.service.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/slovaro")
public class Controller {
    @Autowired
    private SourceService sourceService;
    @GetMapping("/ololo")
    public String getStatus(){
        return "Ololo, I'm successful cucumber!";
    }

    @GetMapping("/sources")
    public List<Source> getSources(){
        return sourceService.getSources();
    }
}
