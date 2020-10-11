package com.example.minesweeper.service;

import com.example.minesweeper.domain.Game;
import com.example.minesweeper.domain.GameState;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.exception.MinesweeperException;
import com.example.minesweeper.helper.LogHelper;
import com.example.minesweeper.service.persistence.GameInMemoryPersistenceService;
import com.example.minesweeper.service.persistence.PlayerInMemoryPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * @return an instance of a Game complete with the parameters requested
     */
    public Game createGame(Integer playerId, Integer height, Integer width, Integer mineQuantity) {
        var game = new Game();
        game.setPlayer(this.playerPersistenceService.getPlayerById(playerId));
        game.setGrid(this.gridService.generateGrid(height, width, mineQuantity));
        game.setState(GameState.PLAYING);
        game.setStartTime(LocalDateTime.now());
        this.gamePersistenceService.saveGame(game);
        return game;
    }

    public Game getGameById(Integer id) {
        return this.gamePersistenceService.getGameById(id);
    }

    public Game clickSquare(Integer gameId, int x, int y) {
        Game game = this.getGameById(gameId);
        // TODO complete actions
        return game;
    }

    public Game flagSquare(Integer gameId, int x, int y) {
        // verify can be flagged
        Game game = this.getGameById(gameId);
        if (GameState.FINISHED.equals(game.getState()))
            throw new MinesweeperException("Finished game can not be modified.", HttpStatus.BAD_REQUEST);

        var square = game.getGrid()[y][x];
        if (SquareState.UNCOVERED.equals(square.getState()))
            throw new MinesweeperException("Uncovered square can not be flagged.", HttpStatus.BAD_REQUEST);
            // change status
        else if (SquareState.COVERED.equals(square.getState()))
            square.setState(SquareState.FLAGGED);
        else if (SquareState.FLAGGED.equals(square.getState()))
            square.setState(SquareState.COVERED);

        // save and return
        game = this.gamePersistenceService.saveGame(game);
        LogHelper.logGrid(game.getGrid());
        return game;
    }


}
