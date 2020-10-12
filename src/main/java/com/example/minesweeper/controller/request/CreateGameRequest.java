package com.example.minesweeper.controller.request;

public class CreateGameRequest {
    private Integer playerId;
    private Integer height;
    private Integer width;
    private Integer mineQuantity;

    public CreateGameRequest(Integer playerId, Integer height, Integer width, Integer mineQuantity) {
        this.playerId = playerId;
        this.height = height;
        this.width = width;
        this.mineQuantity = mineQuantity;
    }

    public CreateGameRequest() {
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
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

    public Integer getMineQuantity() {
        return mineQuantity;
    }

    public void setMineQuantity(Integer mineQuantity) {
        this.mineQuantity = mineQuantity;
    }
}
