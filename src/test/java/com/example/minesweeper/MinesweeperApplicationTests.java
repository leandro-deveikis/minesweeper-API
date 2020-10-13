package com.example.minesweeper;

import com.example.minesweeper.controller.GameController;
import com.example.minesweeper.controller.PlayerController;
import com.example.minesweeper.controller.request.CreateGameRequest;
import com.example.minesweeper.controller.request.CreatePlayerRequest;
import com.example.minesweeper.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a simple test to check some implementations.
 * This can be really improved to get more coverage and details
 */
@SpringBootTest
@ContextConfiguration(
        classes = {MinesweeperApplication.class, TestConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@TestPropertySource(locations = "classpath:application.properties")
class MinesweeperApplicationTests {

    @Autowired
    private PlayerController playerController;
    @Autowired
    private GameController gameController;

    @Test
    void contextLoads() {
    }

    @Test
    void createAndGetPlayer() {
        String test_name_player = "Test Name Player";

        CreatePlayerRequest request = new CreatePlayerRequest();
        request.setName(test_name_player);

        Player outputCreate = this.playerController.createPlayer(request);
        assertNotNull(outputCreate);
        assertNotNull(outputCreate.getId());

        Player outputGet = this.playerController.getPlayerById(outputCreate.getId());
        assertNotNull(outputGet);
        assertNotNull(outputGet.getId());
        assertEquals(test_name_player, outputGet.getName());
    }

    @Test
    void createAndGetGame() {
        // needed player
        String test_name_player = "Test Name Player";
        var createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setName(test_name_player);
        var player = this.playerController.createPlayer(createPlayerRequest);

        // Test - create game
        var request = new CreateGameRequest(player.getId(), 10, 15, 60);
        var game = this.gameController.createGame(request);

        assertNotNull(game);
        assertNotNull(game.getId());
        assertNotNull(game.getGrid());
        assertNotNull(game.getPlayer());
        assertNotNull(game.getStartTime());
        assertNotNull(game.getState());

        // Test - get by id

        var outputGetGame = this.gameController.getGameById(game.getId());
        assertNotNull(outputGetGame);
        assertNotNull(outputGetGame.getId());
    }
}
