package com.example.minesweeper.controller.exceptionhandler;

import com.example.minesweeper.exception.MinesweeperException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Generic exception handler to return the http code and error message to the user
 */
@ControllerAdvice
public class GenericExceptionHandler {
    private static final Log LOGGER = LogFactory.getLog(GenericExceptionHandler.class);

    @ExceptionHandler(value = MinesweeperException.class)
    public ResponseEntity<Map> exception(MinesweeperException exception) {
        LOGGER.error("Exception caught with message: " + exception.getMessage(), exception);
        var response = Map.of(
                "message", exception.getMessage(),
                "error_code", String.valueOf(exception.getHttpCode().value())
        );
        return new ResponseEntity<>(response, exception.getHttpCode());
    }
}
