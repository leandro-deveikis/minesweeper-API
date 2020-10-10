package com.example.minesweeper.service;

import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Main Game services
 */
@Component
public class GameService {
    private GridService gridService;

    @Autowired
    public GameService(GridService gridService) {
        this.gridService = gridService;
    }

    /**
     * Creates a game given the attribute parameters
     *
     * @param request received from the user
     * @return an instance of a Game complete with the parameters requested
     */
    public Game createGame(CreateGameRequest request) {
        // steps
        // TODO Create grid matrix
        // TODO complete grid with mines and other values
        // TODO persist game

        var grid = gridService.generateGrid(request.getHeight(), request.getWidth(), request.getMineQuantity());
        var game = new Game();
        return game;
    }


}
