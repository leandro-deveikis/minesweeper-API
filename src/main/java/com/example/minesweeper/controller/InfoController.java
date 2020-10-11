package com.example.minesweeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Value("${projectVersion}")
    private String projectVersion;

    @RequestMapping
    public Map home() {
        return Map.of(
                "project_name", "Minesweeper-API",
                "version", this.projectVersion,
                "author", "Leandro Deveikis <leandro.deveikis@gmail.com>",
                "repo", "https://github.com/leandro-deveikis/minesweeper-API"
        );
    }
}
