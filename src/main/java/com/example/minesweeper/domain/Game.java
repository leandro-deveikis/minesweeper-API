package com.example.minesweeper.domain;

import java.time.LocalDateTime;

/**
 * Representation of a Game being played or finished.
 */
public class Game {
    private Integer id;
    private Player player;
    private Square[][] grid;
    private LocalDateTime startTime;
    private GameState state;
    private GameResult result;

    // TODO: This may not be necessary
    public Game() {

    }

    public Game(Integer id, Player player, Square[][] grid) {
        this.id = id;
        this.player = player;
        this.grid = grid;
        this.state = GameState.PLAYING;
        this.startTime = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }
}
