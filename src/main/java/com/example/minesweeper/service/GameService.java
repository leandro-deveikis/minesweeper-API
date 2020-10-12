package com.example.minesweeper.service;

import com.example.minesweeper.domain.*;
import com.example.minesweeper.exception.MinesweeperException;
import com.example.minesweeper.helper.LogHelper;
import com.example.minesweeper.helper.SquareHelper;
import com.example.minesweeper.service.persistence.GameInMemoryPersistenceService;
import com.example.minesweeper.service.persistence.PlayerInMemoryPersistenceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Main Game services
 */
@Component
public class GameService {
    private static final Log LOGGER = LogFactory.getLog(GameService.class);

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
        game.setHeight(height);
        game.setWidth(width);
        game = this.gamePersistenceService.saveGame(game);
        LOGGER.info("Game created with id: " + game.getId());
        return game;
    }

    public Game getGameById(Integer id) {
        return this.gamePersistenceService.getGameById(id);
    }

    public Game clickSquare(Integer gameId, int x, int y) {
        var game = this.getGameById(gameId);
        this.validateGameCanAction(game);
        var square = game.getGrid()[y][x];

        // check if flagged   -> throw exception
        if (SquareState.FLAGGED.equals(square.getState()))
            throw new MinesweeperException("Invalid operation - Clicking flagged square", HttpStatus.BAD_REQUEST);

        // check if uncovered -> throw exception
        if (SquareState.UNCOVERED.equals(square.getState()))
            throw new MinesweeperException("Invalid operation - Clicking already uncovered square", HttpStatus.BAD_REQUEST);

        // check if mine      -> end game loose
        if (SquareValue.MINE.equals(square.getValue())) {
            LOGGER.info("Mine clicked, finishing game.");
            square.setState(SquareState.UNCOVERED);
            this.finishGame(game, GameResult.LOST);
        } else {
            // -- if number = 0   -> uncover other squares
            SquareHelper.uncoverSquare(x, y, game);

            // check if game is finished (all of the remaining uncover squares are mines)
            // -- if finished     -> game won
            if (this.checkGameFinished(game)) {
                this.finishGame(game, GameResult.WON);
            }
        }

        game = this.gamePersistenceService.saveGame(game);
        LogHelper.logGrid(game.getGrid());
        return game;
    }

    private void finishGame(Game game, GameResult gameResult) {
        LOGGER.info("Finishing game with id " + game.getId() + " with result " + gameResult);
        game.setResult(gameResult);
        game.setState(GameState.FINISHED);
        game.setFinishTime(LocalDateTime.now());
    }

    private boolean checkGameFinished(Game game) {
        var grid = game.getGrid();
        var finished = Arrays.stream(grid)
                .flatMap(squares -> Stream.of(squares))
                .allMatch(square ->
                        SquareState.UNCOVERED.equals(square.getState()) ||
                                SquareValue.MINE.equals(square.getValue()));
        LOGGER.info("Game " + game.getId() + " finished: " + finished);
        return finished;
    }

    public Game flagSquare(Integer gameId, int x, int y) {
        // verify can be flagged
        var game = this.getGameById(gameId);
        this.validateGameCanAction(game);

        var square = game.getGrid()[y][x];
        if (SquareState.UNCOVERED.equals(square.getState()))
            throw new MinesweeperException("Uncovered square can not be flagged.", HttpStatus.BAD_REQUEST);

        // reverse status
        if (SquareState.COVERED.equals(square.getState()))
            square.setState(SquareState.FLAGGED);
        else if (SquareState.FLAGGED.equals(square.getState()))
            square.setState(SquareState.COVERED);

        // save and return
        game = this.gamePersistenceService.saveGame(game);
        LogHelper.logGrid(game.getGrid());
        return game;
    }

    private void validateGameCanAction(Game game) {
        if (GameState.FINISHED.equals(game.getState()))
            throw new MinesweeperException("Finished game can not be modified.", HttpStatus.BAD_REQUEST);
    }
}