package com.example.minesweeper.controller.request;

/**
 * This request dto will be used for click action
 */
public class ClickActionRequest {
    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
