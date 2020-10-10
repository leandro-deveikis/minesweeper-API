package com.example.minesweeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {

    @Value("${projectVersion}")
    private String projectVersion;

    @RequestMapping
    public Map home() {
        Map<String, String> info = new HashMap<>();
        info.put("project_name", "Minesweeper-API");
        info.put("version", this.projectVersion);
        info.put("author", "Leandro Deveikis <leandro.deveikis@gmail.com>");
        info.put("repo", "https://github.com/leandro-deveikis/minesweeper-API");
        return info;
    }
}
