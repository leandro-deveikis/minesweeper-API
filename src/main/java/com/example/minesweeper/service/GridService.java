package com.example.minesweeper.service;

import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.domain.SquareValue;
import com.example.minesweeper.helper.LogHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Grid related operations
 */
@Component
public class GridService {

    /**
     * Generates a grid complete with mines and other squares
     */
    public Square[][] generateGrid(Integer height, Integer width, Integer mineQuantity) {
        // create the grid
        Square[][] grid = new Square[height][width];
        this.completeMines(height, width, mineQuantity, grid);
        this.completeOtherSquares(height, width, grid);
        // finished
        LogHelper.logGrid(grid);
        return grid;
    }

    private void completeMines(Integer height, Integer width, Integer mineQuantity, Square[][] grid) {
        // randomly complete the mines
        Random random = new Random();
        for (int i = 0; i < mineQuantity; i++) {
            int y;
            int x;
            do {
                y = random.nextInt(height);
                x = random.nextInt(width);
            } while (grid[y][x] != null);
            // the square is empty, so we complete with a mine
            grid[y][x] = new Square(SquareState.UNCOVERED, SquareValue.MINE);
        }
    }

    private void completeOtherSquares(Integer height, Integer width, Square[][] grid) {
        //complete the rest of the squares
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == null) {
                    var number = this.calculateNumberForSquare(grid, height, width, i, j);
                    grid[i][j] = new Square(SquareState.UNCOVERED, SquareValue.NUMBER, number);
                }
            }
        }
    }

    /**
     * This will calculate the number of mines adjacent to a position.
     * TODO: This method probably can be refactor or improved
     */
    public int calculateNumberForSquare(Square[][] grid, Integer height, Integer width, int i, int j) {
        int mineCount = 0;
        // upper line
        int upperLine = i - 1;
        if (upperLine >= 0) {
            if ((j - 1 >= 0) && this.containsMine(grid[upperLine][j - 1])) mineCount++;
            if (this.containsMine(grid[upperLine][j])) mineCount++;
            if ((j + 1 <= width - 1) && this.containsMine(grid[upperLine][j + 1])) mineCount++;
        }
        // same line
        if ((j - 1 >= 0) && this.containsMine(grid[i][j - 1])) mineCount++;
        if ((j + 1 <= width - 1) && this.containsMine(grid[i][j + 1])) mineCount++;

        // lower line
        int lowerLine = i + 1;
        if (lowerLine <= height - 1) {
            if ((j - 1 >= 0) && this.containsMine(grid[lowerLine][j - 1])) mineCount++;
            if (this.containsMine(grid[lowerLine][j])) mineCount++;
            if ((j + 1 <= width - 1) && this.containsMine(grid[lowerLine][j + 1])) mineCount++;
        }
        return mineCount;
    }

    /**
     * Null-safe validation
     */
    public boolean containsMine(Square square) {
        return square != null && SquareValue.MINE.equals(square.getValue());
    }
}
