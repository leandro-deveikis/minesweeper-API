package com.example.minesweeper.service;

import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.domain.SquareValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for {@link GridService}
 * <p>
 * - Note: more test cases could be added.
 */
class GridServiceTest {
    private GridService gridService;

    @BeforeEach
    public void beforeEach() {
        this.gridService = new GridService();
    }

    @Test
    public void testGenerateGridService_H10_W15_M17_ok() {
        Integer height = 10;
        Integer width = 15;
        Integer mines = 17;

        var grid = this.gridService.generateGrid(height, width, mines);

        validateGrid(grid, height, width, mines);
    }

    @Test
    public void testGenerateGridService_H2_W2_M1_ok() {
        Integer height = 2;
        Integer width = 2;
        Integer mines = 1;

        var grid = this.gridService.generateGrid(height, width, mines);

        validateGrid(grid, height, width, mines);
    }

    @Test
    public void testGenerateGridService_H25_W30_M100_ok() {
        Integer height = 25;
        Integer width = 30;
        Integer mines = 100;

        var grid = this.gridService.generateGrid(height, width, mines);

        validateGrid(grid, height, width, mines);
    }

    private void validateGrid(Square[][] grid, Integer height, Integer width, int mines) {
        assertNotNull(grid);
        assertEquals(height, grid.length);
        assertEquals(width, grid[0].length);
        assertEquals(mines, this.getMineCount(height, width, grid));
        // all should be covered when first created
        Arrays.stream(grid)
                .flatMap(squares -> Stream.of(squares))
                .forEach(square ->
                        assertEquals(SquareState.COVERED, square.getState())
                );
    }

    private long getMineCount(Integer height, Integer width, Square[][] grid) {
        return Arrays.stream(grid)
                .flatMap(squares -> Stream.of(squares))
                .filter(square -> SquareValue.MINE.equals(square.getValue()))
                .count();
    }
}