package com.example.minesweeper.controller;

import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.domain.Game;
import com.example.minesweeper.service.GameService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Log LOGGER = LogFactory.getLog(GameController.class);
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Game getGame(@PathVariable() Integer id) {
        return gameService.getGameById(id);
    }

    @PostMapping
    @ResponseBody
    public Game createGame(CreateGameRequest request) {
        // TODO step 1 - Validate request
        // TODO Create exceptions to throw - return error value
        return this.gameService.createGame(request);
    }
}
