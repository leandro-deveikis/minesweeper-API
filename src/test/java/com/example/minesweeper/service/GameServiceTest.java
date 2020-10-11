package com.example.minesweeper.service;

import com.example.minesweeper.GridMockGenerator;
import com.example.minesweeper.domain.Game;
import com.example.minesweeper.domain.Square;
import com.example.minesweeper.domain.SquareState;
import com.example.minesweeper.service.persistence.GameInMemoryPersistenceService;
import com.example.minesweeper.service.persistence.PlayerInMemoryPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Mock
    private GameInMemoryPersistenceService gamePersistenceService;
    @Mock
    private PlayerInMemoryPersistenceService playerPersistenceService;
    private Square[][] gridMock;
    private Game gameMock;

    @BeforeEach
    public void beforeEach() {
        gridMock = GridMockGenerator.getMocked3x3Grid();
        gameMock = new Game();
        // at this time we only use this param
        gameMock.setGrid(gridMock);

        this.gameService = new GameService(this.gridService, this.gamePersistenceService, this.playerPersistenceService);

        // save method returns the same
        when(gamePersistenceService.saveGame(any())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    void createGame_ok() {
        when(gridService.generateGrid(any(), any(), any())).thenReturn(gridMock);

        var game = this.gameService.createGame(5, 3, 3, 2);
        assertNotNull(game);
    }

    @Test
    void flagSquare_ok() {
        when(gamePersistenceService.getGameById(any())).thenReturn(gameMock);

        var game = this.gameService.flagSquare(2, 0, 0);
        assertEquals(SquareState.FLAGGED, game.getGrid()[0][0].getState());
    }
}