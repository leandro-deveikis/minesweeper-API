package com.example.minesweeper.helper;

import com.example.minesweeper.domain.Game;
import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.domain.SquareValue;

public abstract class SquareHelper {
    /**
     * This will calculate the number of mines adjacent to a position.
     * TODO: This method probably can be refactor or improved
     */
    public static int calculateNumberForSquare(Square[][] grid, Integer height, Integer width, int i, int j) {
        int mineCount = 0;
        // upper line
        int upperLine = i - 1;
        if (upperLine >= 0) {
            if ((j - 1 >= 0) && containsMine(grid[upperLine][j - 1])) mineCount++;
            if (containsMine(grid[upperLine][j])) mineCount++;
            if ((j + 1 <= width - 1) && containsMine(grid[upperLine][j + 1])) mineCount++;
        }
        // same line
        if ((j - 1 >= 0) && containsMine(grid[i][j - 1])) mineCount++;
        if ((j + 1 <= width - 1) && containsMine(grid[i][j + 1])) mineCount++;

        // lower line
        int lowerLine = i + 1;
        if (lowerLine <= height - 1) {
            if ((j - 1 >= 0) && containsMine(grid[lowerLine][j - 1])) mineCount++;
            if (containsMine(grid[lowerLine][j])) mineCount++;
            if ((j + 1 <= width - 1) && containsMine(grid[lowerLine][j + 1])) mineCount++;
        }
        return mineCount;
    }

    /**
     * Null-safe validation
     */
    public static boolean containsMine(Square square) {
        return square != null && SquareValue.MINE.equals(square.getValue());
    }


    public static void uncoverSquare(int x, int y, Game game) {
        // this will avoid problems with the array
        if (x < 0 || x >= game.getWidth()) return;
        if (y < 0 || y >= game.getHeight()) return;

        Square square = game.getGrid()[y][x];
        // avoid infinite loop
        if (SquareState.UNCOVERED.equals(square.getState())) return;

        square.setState(SquareState.UNCOVERED);

        if (square.getNumber() != null && square.getNumber() == 0) {
            uncoverSquare(x - 1, y - 1, game);
            uncoverSquare(x - 1, y, game);
            uncoverSquare(x - 1, y + 1, game);
            uncoverSquare(x, y - 1, game);
            uncoverSquare(x, y + 1, game);
            uncoverSquare(x + 1, y - 1, game);
            uncoverSquare(x + 1, y, game);
            uncoverSquare(x + 1, y + 1, game);
        }
    }
}
