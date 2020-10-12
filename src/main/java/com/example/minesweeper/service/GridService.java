package com.example.minesweeper.service;

import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.domain.SquareValue;
import com.example.minesweeper.helper.LogHelper;
import com.example.minesweeper.helper.SquareHelper;
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
            grid[y][x] = new Square(SquareState.COVERED, SquareValue.MINE);
        }
    }

    private void completeOtherSquares(Integer height, Integer width, Square[][] grid) {
        //complete the rest of the squares
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == null) {
                    var number = SquareHelper.calculateNumberForSquare(grid, height, width, i, j);
                    grid[i][j] = new Square(SquareState.COVERED, SquareValue.NUMBER, number);
                }
            }
        }
    }
}
