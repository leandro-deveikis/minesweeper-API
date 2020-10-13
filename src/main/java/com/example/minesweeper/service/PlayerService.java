package com.example.minesweeper.service;

import com.example.minesweeper.controller.request.CreatePlayerRequest;
import com.example.minesweeper.domain.Player;
import com.example.minesweeper.exception.MinesweeperException;
import com.example.minesweeper.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Integer id) {
        return this.playerRepository.findById(id)
                .orElseThrow(() -> new MinesweeperException("Player not found: " + id, HttpStatus.NOT_FOUND));
    }

    public Player createPlayer(CreatePlayerRequest request) {
        var player = new Player();
        player.setName(request.getName());
        return this.playerRepository.save(player);
    }
}
