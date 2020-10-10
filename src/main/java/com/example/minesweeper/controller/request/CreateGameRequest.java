package com.example.minesweeper.controller.request;

public class CreateGameRequest {
    private Integer userId;
    private Integer height;
    private Integer width;
    private Integer mineQuantity;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
