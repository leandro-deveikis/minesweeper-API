package com.example.minesweeper.controller.validator;

import com.example.minesweeper.controller.request.CreatePlayerRequest;
import com.example.minesweeper.exception.MinesweeperException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PlayerRequestValidator {
    public void validateCreatePlayerRequest(CreatePlayerRequest request) {
        if (request == null)
            throw new MinesweeperException("Request body is required", HttpStatus.BAD_REQUEST);
        if (StringUtils.isEmpty(request.getName()))
            throw new MinesweeperException("Name is required", HttpStatus.BAD_REQUEST);
    }
}
