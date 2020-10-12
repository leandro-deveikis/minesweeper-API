package com.example.minesweeper.controller;

import com.example.minesweeper.controller.request.CreatePlayerRequest;
import com.example.minesweeper.controller.validator.PlayerRequestValidator;
import com.example.minesweeper.domain.Player;
import com.example.minesweeper.service.PlayerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private static final Log LOGGER = LogFactory.getLog(PlayerController.class);
    private PlayerService playerService;
    private PlayerRequestValidator playerRequestValidator;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerRequestValidator playerRequestValidator) {
        this.playerService = playerService;
        this.playerRequestValidator = playerRequestValidator;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Player getPlayerById(@PathVariable Integer id) {
        LOGGER.info("PlayerController.getPlayerById called.");
        return this.playerService.getPlayerById(id);
    }

    @PostMapping
    @ResponseBody
    public Player createPlayer(@RequestBody CreatePlayerRequest request) {
        LOGGER.info("PlayerController.createPlayer called.");
        this.playerRequestValidator.validateCreatePlayerRequest(request);
        return this.playerService.createPlayer(request);
    }
}
