package com.example.minesweeper.controller;

import com.example.minesweeper.controller.request.CreatePlayerRequest;
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

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Player getPlayer(@PathVariable Integer id) {
        return this.playerService.getPlayerById(id);
    }

    @PostMapping
    @ResponseBody
    public Player createPlayer(@RequestBody CreatePlayerRequest request) {
        return this.playerService.createPlayer(request);
    }
}
