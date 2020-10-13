package com.example.minesweeper.controller.request;

import com.example.minesweeper.domain.FlagType;

/**
 * This request dto will be used for both clicking and flagging a square
 */
public class FlagActionRequest {
    private Integer x;
    private Integer y;
    private FlagType flagType;

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

    public FlagType getFlagType() {
        return flagType;
    }

    public void setFlagType(FlagType flagType) {
        this.flagType = flagType;
    }
}
