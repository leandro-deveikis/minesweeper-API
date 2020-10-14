package com.example.minesweeper.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    private static final Log LOGGER = LogFactory.getLog(InfoController.class);

    @Value("${projectVersion}")
    private String projectVersion;

    @GetMapping
    public Map getInfo() {
        LOGGER.info("InfoController.info called.");
        return Map.of(
                "projectName", "Minesweeper-API",
                "version", this.projectVersion,
                "author", "Leandro Deveikis <leandro.deveikis@gmail.com>",
                "repo", "https://github.com/leandro-deveikis/minesweeper-API"
        );
    }
}
