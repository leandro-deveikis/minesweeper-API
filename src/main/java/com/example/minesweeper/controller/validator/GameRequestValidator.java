package com.example.minesweeper.controller.validator;

import com.example.minesweeper.controller.request.ClickActionRequest;
import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.controller.request.FlagActionRequest;
import com.example.minesweeper.exception.MinesweeperException;
import com.example.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Validator for request for {@link com.example.minesweeper.controller.GameController}
 */
@Component
public class GameRequestValidator {
    private GameService gameService;

    @Autowired
    public GameRequestValidator(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Validates click requests.
     * Validates:
     * - None of the param is missing.
     * - game is found by the ID.
     * - X and Y are within the width and length of the grid
     */
    public void validateClickActionRequest(Integer gameId, ClickActionRequest actionRequest) {
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

        var game = gameService.getGameById(gameId);
        if (y < 0 || y > game.getHeight() - 1)
            throw new MinesweeperException("Y param is invalid", HttpStatus.BAD_REQUEST);
        if (x < 0 || x > game.getWidth() - 1)
            throw new MinesweeperException("X param is invalid", HttpStatus.BAD_REQUEST);
    }

    /**
     * Validates flag requests.
     * Validates:
     * - None of the param is missing.
     * - game is found by the ID.
     * - X and Y are within the width and length of the grid
     */
    public void validateFlagActionRequest(Integer gameId, FlagActionRequest actionRequest) {
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

        if (actionRequest.getFlagType() == null)
            throw new MinesweeperException("flagType param is required", HttpStatus.BAD_REQUEST);

        var game = gameService.getGameById(gameId);
        if (y < 0 || y > game.getHeight() - 1)
            throw new MinesweeperException("Y param is invalid", HttpStatus.BAD_REQUEST);
        if (x < 0 || x > game.getWidth() - 1)
            throw new MinesweeperException("X param is invalid", HttpStatus.BAD_REQUEST);
    }

    public void validateCreateGameRequest(CreateGameRequest request) {
        if (request == null)
            throw new MinesweeperException("Request body is required", HttpStatus.BAD_REQUEST);
        if (request.getWidth() == null || request.getWidth() < 0)
            throw new MinesweeperException("Width is required and should be higher than 0", HttpStatus.BAD_REQUEST);
        if (request.getHeight() == null || request.getHeight() < 0)
            throw new MinesweeperException("Height is required and should be higher than 0", HttpStatus.BAD_REQUEST);
        if (request.getMineQuantity() == null || request.getMineQuantity() < 0)
            throw new MinesweeperException("MineQuantity is required and should be higher than 0", HttpStatus.BAD_REQUEST);
        if (request.getPlayerId() == null)
            throw new MinesweeperException("PlayerId is required", HttpStatus.BAD_REQUEST);
    }
}
