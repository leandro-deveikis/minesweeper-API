package com.example.minesweeper.domain;

public class Square {
    private SquareState state;
    private SquareValue value;
    // this is only set if VALUE is number.
    private Integer number;

    public Square(SquareState state, SquareValue value) {
        this.state = state;
        this.value = value;
    }

    public Square(SquareState state, SquareValue value, Integer number) {
        this.state = state;
        this.value = value;
        this.number = number;
    }

    public SquareValue getValue() {
        return value;
    }

    public void setValue(SquareValue value) {
        this.value = value;
    }

    public SquareState getState() {
        return state;
    }

    public void setState(SquareState state) {
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
