package com.example.minesweeper.service;

import com.example.minesweeper.controller.request.CreatePlayerRequest;
import com.example.minesweeper.domain.Player;
import com.example.minesweeper.service.persistence.PlayerInMemoryPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {
    private PlayerInMemoryPersistenceService playerPersistence;

    @Autowired
    public PlayerService(PlayerInMemoryPersistenceService playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public Player getPlayerById(Integer id) {
        return this.playerPersistence.getPlayerById(id);
    }

    public Player createPlayer(CreatePlayerRequest request) {
        var player = new Player();
        player.setName(request.getName());
        return this.playerPersistence.savePlayer(player);
    }
}
