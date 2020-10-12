package com.example.minesweeper.domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Representation of a Game being played or finished.
 */
public class Game {
    private Integer id;
    private Player player;
    private Square[][] grid;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private GameState state;
    private GameResult result;
    private Integer height;
    private Integer width;

    public void setId(Integer id) {
        this.id = id;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * this will be returned in the json response. will represent the
     * amount of time (in seconds) played by de user
     */
    public long getTimeExpended() {
        LocalDateTime endTime;
        if (finishTime != null) {
            endTime = this.finishTime;
        } else {
            endTime = LocalDateTime.now();
        }
        return Duration.between(this.startTime, endTime).toSeconds();
    }
}
