package com.example.minesweeper.controller.request;

/**
 * This request dto will be used for both clicking and flagging a square
 */
public class GameActionRequest {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
