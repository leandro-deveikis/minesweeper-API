package com.example.minesweeper.controller.validator;

import com.example.minesweeper.controller.request.GameActionRequest;
import com.example.minesweeper.exception.MinesweeperException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Validator for request for {@link com.example.minesweeper.controller.GameController}
 */
@Component
public class GameRequestValidator {

    public void validateFlagSquare(Integer gameId, GameActionRequest actionRequest) {
        if (StringUtils.isEmpty(gameId))
            throw new MinesweeperException("GameId is required", HttpStatus.BAD_REQUEST);
        if (actionRequest == null)
            throw new MinesweeperException("X and Y positions are required", HttpStatus.BAD_REQUEST);
        if (StringUtils.isEmpty(actionRequest.getX()))
            throw new MinesweeperException("X param is required", HttpStatus.BAD_REQUEST);
        if (StringUtils.isEmpty(actionRequest.getY()))
            throw new MinesweeperException("Y param is required", HttpStatus.BAD_REQUEST);
    }
}
