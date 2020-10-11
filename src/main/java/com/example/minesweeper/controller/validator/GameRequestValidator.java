package com.example.minesweeper.controller.validator;

import com.example.minesweeper.controller.request.GameActionRequest;
import com.example.minesweeper.exception.MinesweeperException;
import com.example.minesweeper.service.persistence.GameInMemoryPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Validator for request for {@link com.example.minesweeper.controller.GameController}
 */
@Component
public class GameRequestValidator {
    private GameInMemoryPersistenceService gamePersistenceService;

    @Autowired
    public GameRequestValidator(GameInMemoryPersistenceService gamePersistenceService) {
        this.gamePersistenceService = gamePersistenceService;
    }

    /**
     * Used for both click and flag requests.
     * Validates:
     * - None of the param is missing.
     * - game is found by the ID.
     * - X and Y are within the width and length of the grid
     */
    public void validateGameActionRequest(Integer gameId, GameActionRequest actionRequest) {
        if (StringUtils.isEmpty(gameId))
            throw new MinesweeperException("GameId is required", HttpStatus.BAD_REQUEST);

        if (actionRequest == null)
            throw new MinesweeperException("X and Y positions are required", HttpStatus.BAD_REQUEST);

        Integer x = actionRequest.getX();
        Integer y = actionRequest.getY();
        if (x == null)
            throw new MinesweeperException("X param is required", HttpStatus.BAD_REQUEST);
        if (y == null)
            throw new MinesweeperException("Y param is required", HttpStatus.BAD_REQUEST);

        var game = gamePersistenceService.getGameById(gameId);
        if (y < 0 || y > game.getGameHeight() - 1)
            throw new MinesweeperException("Y param is invalid", HttpStatus.BAD_REQUEST);
        if (x < 0 || x > game.getGameWidth() - 1)
            throw new MinesweeperException("X param is invalid", HttpStatus.BAD_REQUEST);
    }
}
