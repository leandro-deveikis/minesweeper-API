package com.example.minesweeper.service;

import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.domain.Game;
import com.example.minesweeper.domain.GameState;
import com.example.minesweeper.service.persistence.GameInMemoryPersistenceService;
import com.example.minesweeper.service.persistence.PlayerInMemoryPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Main Game services
 */
@Component
public class GameService {
    private GridService gridService;
    private GameInMemoryPersistenceService gamePersistenceService;
    private PlayerInMemoryPersistenceService playerPersistenceService;

    @Autowired
    public GameService(GridService gridService, GameInMemoryPersistenceService gamePersistenceService, PlayerInMemoryPersistenceService playerInMemoryPersistenceService) {
        this.gridService = gridService;
        this.gamePersistenceService = gamePersistenceService;
        this.playerPersistenceService = playerInMemoryPersistenceService;
    }

    /**
     * Creates a game given the attribute parameters
     *
     * @param request received from the user
     * @return an instance of a Game complete with the parameters requested
     */
    public Game createGame(CreateGameRequest request) {
        var game = new Game();
        game.setGrid(this.gridService.generateGrid(request.getHeight(), request.getWidth(), request.getMineQuantity()));
        game.setState(GameState.PLAYING);
        game.setPlayer(this.playerPersistenceService.getPlayerById(request.getPlayerId()));
        game.setStartTime(LocalDateTime.now());
        this.gamePersistenceService.saveGame(game);
        return game;
    }

    public Game getGameById(Integer id) {
        return this.gamePersistenceService.getGameById(id);
    }
}
