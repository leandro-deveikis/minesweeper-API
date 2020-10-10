package com.example.minesweeper.service;

import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.domain.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test for {@link GameService}
 */
@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    private GameService gameService;

    @Mock
    private GridService gridService;
    private Square[][] squareMock = new Square[3][3];

    @BeforeEach
    public void beforeEach() {
        this.gameService = new GameService(this.gridService);

        when(gridService.generateGrid(any(), any(), any())).thenReturn(squareMock);
    }

    @Test
    public void createGame_ok() {
        CreateGameRequest request = new CreateGameRequest(5, 3, 3, 5);
        var game = this.gameService.createGame(request);
        assertNotNull(game);
    }
}