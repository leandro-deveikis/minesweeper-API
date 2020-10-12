package com.example.minesweeper.helper;

import com.example.minesweeper.domain.Square;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class LogHelper {
    private static final Log LOGGER = LogFactory.getLog(LogHelper.class);

    /**
     * Logs a representation of the grid to the logger
     */
    public static void logGrid(Square[][] grid) {
        var sb = new StringBuilder("\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(LogHelper.getIcon(grid[i][j]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        LOGGER.info(sb.toString());
    }

    private static String getIcon(Square square) {
        var sb = new StringBuilder();
        switch (square.getState()) {
            case COVERED:
                sb.append("C");
                break;
            case FLAGGED:
                sb.append("F");
                break;
            case UNCOVERED:
                sb.append("U");
                break;
        }
        switch (square.getValue()) {
            case MINE:
                sb.append("(M)");
                break;
            case NUMBER:
                sb.append("(" + square.getNumber().toString() + ")");
                break;
        }
        // should never reach here
        return sb.toString();
    }
}
