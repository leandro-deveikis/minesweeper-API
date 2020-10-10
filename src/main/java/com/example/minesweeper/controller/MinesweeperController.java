package com.example.minesweeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MinesweeperController {

    @Value("${projectVersion}")
    private String projectVersion;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/version")
    @ResponseBody
    public String version() {
        return "Version: " + projectVersion + "\nAuthor: Leandro Deveikis <leandro.deveikis@gmail.com>";
    }
}
