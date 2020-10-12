package com.example.minesweeper;

import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.domain.SquareValue;

public class GridMockGenerator {

    /**
     * This generates a 3x3 grid with 2 mines, al uncovered squares
     * Like this:
     * 2 2 1
     * M M 1
     * 2 2 1
     */
    public static Square[][] getMocked3x3Grid() {
        var grid = new Square[3][3];
        grid[0][0] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[0][1] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[0][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        grid[1][0] = new Square(SquareState.COVERED, SquareValue.MINE);
        grid[1][1] = new Square(SquareState.COVERED, SquareValue.MINE);
        grid[1][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        grid[2][0] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[2][1] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[2][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        return grid;
    }


    /**
     * This generates a 4x4 grid with 2 mines, al uncovered squares
     * Like this:
     * 2 2 1 0
     * M M 1 0
     * 2 2 1 0
     */
    public static Square[][] getMocked3x4Grid() {
        var grid = new Square[3][4];
        grid[0][0] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[0][1] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[0][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        grid[0][3] = new Square(SquareState.COVERED, SquareValue.NUMBER, 0);
        grid[1][0] = new Square(SquareState.COVERED, SquareValue.MINE);
        grid[1][1] = new Square(SquareState.COVERED, SquareValue.MINE);
        grid[1][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        grid[1][3] = new Square(SquareState.COVERED, SquareValue.NUMBER, 0);
        grid[2][0] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[2][1] = new Square(SquareState.COVERED, SquareValue.NUMBER, 2);
        grid[2][2] = new Square(SquareState.COVERED, SquareValue.NUMBER, 1);
        grid[2][3] = new Square(SquareState.COVERED, SquareValue.NUMBER, 0);
        return grid;
    }
}
