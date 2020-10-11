package com.example.minesweeper.controller;

import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.controller.request.GameActionRequest;
import com.example.minesweeper.controller.validator.GameRequestValidator;
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
    private GameRequestValidator requestValidator;

    @Autowired
    public GameController(GameService gameService, GameRequestValidator requestValidator) {
        this.gameService = gameService;
        this.requestValidator = requestValidator;
    }

    @GetMapping("/{gameId}")
    @ResponseBody
    public Game getGame(@PathVariable Integer gameId) {
        return gameService.getGameById(gameId);
    }

    @PostMapping
    @ResponseBody
    public Game createGame(@RequestBody CreateGameRequest request) {
        // TODO Validate request
        // TODO Create exceptions to throw - return error value
        return this.gameService.createGame(request);
    }

    @PostMapping("/{gameId}/click")
    @ResponseBody
    public Game clickSquare(@PathVariable Integer gameId, @RequestBody GameActionRequest actionRequest) {
        // TODO Validate request
        // TODO Create exceptions to throw - return error value
        return this.gameService.clickSquare(gameId, actionRequest);
    }

    @PostMapping("/{gameId}/flag")
    @ResponseBody
    public Game flagSquare(@PathVariable Integer gameId, @RequestBody GameActionRequest actionRequest) {
        this.requestValidator.validateFlagSquare(gameId, actionRequest);
        return this.gameService.flagSquare(gameId, actionRequest);
    }
}
