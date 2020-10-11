package com.example.minesweeper.exception;


import org.springframework.http.HttpStatus;

/**
 * This is a simple implementation of a generic exception.
 * Each service will define the http code and the error message to return to the user.
 * This can be improved refactoring this (for instance, having a hierarchy of classes or an enum
 * to group the codes and error message).
 */
public class MinesweeperException extends RuntimeException {
    private HttpStatus httpCode;

    public MinesweeperException(String message, HttpStatus httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }
}
