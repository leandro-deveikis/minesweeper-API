package com.example.minesweeper.service;

import com.example.minesweeper.GridMockGenerator;
import com.example.minesweeper.domain.*;
import com.example.minesweeper.persistence.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private GameRepository gameRepository;
    @Mock
    private PlayerService playerService;
    private Square[][] gridMock;
    private Game gameMock;

    @BeforeEach
    public void beforeEach() {
        gridMock = GridMockGenerator.getMocked3x3Grid();
        gameMock = new Game();
        // at this time we only use this param
        gameMock.setGrid(gridMock);

        this.gameService = new GameService(this.gridService, this.playerService, this.gameRepository);

        // save method returns the same
        when(gameRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    void createGame_ok() {
        mockPlayer();
        when(gridService.generateGrid(any(), any(), any())).thenReturn(gridMock);

        var game = this.gameService.createGame(5, 3, 3, 2);
        assertNotNull(game);
    }

    private void mockPlayer() {
        Player mockPlayer = new Player();
        mockPlayer.setName("Mock player Name");
        mockPlayer.setId(5);
        when(playerService.getPlayerById(eq(5))).thenReturn(mockPlayer);
    }

    @Test
    void flagSquare_ok() {
        when(gameRepository.findById(any())).thenReturn(Optional.of(gameMock));

        var game = this.gameService.flagSquare(2, 0, 0);
        assertEquals(SquareState.FLAGGED, game.getGrid()[0][0].getState());

        // now reverse it
        var game2 = this.gameService.flagSquare(2, 0, 0);
        assertEquals(SquareState.COVERED, game2.getGrid()[0][0].getState());
    }


    /***********************************************/
    // NOTE: This is a test of a complete game. This should be separated within different tests
    // Comments of the state of the board are shown being:
    // State: C = covered - U = Uncovered - F : Flagged
    // Value: [number] or M : mine
    @Test
    void completeGame_ok_win() {
        Square[][] grid = GridMockGenerator.getMocked3x4Grid();
        when(gridService.generateGrid(any(), any(), any())).thenReturn(grid);

        var game = this.gameService.createGame(5, 3, 4, 2);
        assertNotNull(game);

        when(gameRepository.findById(any())).thenReturn(Optional.of(game));

        /*  Game state
            C(2) C(2) C(1) C(0)
            C(M) C(M) C(1) C(0)
            C(2) C(2) C(1) C(0)
         */

        game = this.gameService.flagSquare(5, 1, 0);
        assertEquals(SquareState.FLAGGED, game.getGrid()[0][1].getState());

         /*  Game state
            C(2) F(2) C(1) C(0)
            C(M) C(M) C(1) C(0)
            C(2) C(2) C(1) C(0)
         */

        game = this.gameService.flagSquare(5, 1, 0);
        assertEquals(SquareState.COVERED, game.getGrid()[0][1].getState());

        /*  Game state
            C(2) C(2) C(1) C(0)
            C(M) C(M) C(1) C(0)
            C(2) C(2) C(1) C(0)
         */

        game = this.gameService.clickSquare(5, 3, 1);
        assertEquals(SquareState.UNCOVERED, game.getGrid()[0][3].getState());
        assertEquals(SquareState.UNCOVERED, game.getGrid()[1][3].getState());
        assertEquals(SquareState.UNCOVERED, game.getGrid()[2][3].getState());
        assertEquals(SquareState.UNCOVERED, game.getGrid()[0][2].getState());
        assertEquals(SquareState.UNCOVERED, game.getGrid()[1][2].getState());
        assertEquals(SquareState.UNCOVERED, game.getGrid()[2][2].getState());

        /*  Game state
            C(2) C(2) U(1) U(0)
            C(M) C(M) U(1) U(0)
            C(2) C(2) U(1) U(0)
         */

        // complete other hits to win
        game = this.gameService.clickSquare(5, 1, 0);
        game = this.gameService.clickSquare(5, 1, 2);
        game = this.gameService.clickSquare(5, 0, 0);
        game = this.gameService.clickSquare(5, 0, 2);

        /*  Game state
            U(2) U(2) U(1) U(0)
            C(M) C(M) U(1) U(0)
            U(2) U(2) U(1) U(0)
         */

        assertEquals(GameState.FINISHED, game.getState());
        assertEquals(GameResult.WON, game.getResult());
        assertNotNull(game.getFinishTime());
    }

    @Test
    void completeGame_ok_loose() {
        Square[][] grid = GridMockGenerator.getMocked3x4Grid();
        when(gridService.generateGrid(any(), any(), any())).thenReturn(grid);

        var game = this.gameService.createGame(5, 3, 4, 2);
        assertNotNull(game);

        when(gameRepository.findById(any())).thenReturn(Optional.of(game));

        game = this.gameService.clickSquare(5, 1, 1);
        assertEquals(GameState.FINISHED, game.getState());
        assertEquals(GameResult.LOST, game.getResult());
        assertNotNull(game.getFinishTime());
    }
}