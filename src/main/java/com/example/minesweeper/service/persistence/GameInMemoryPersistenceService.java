package com.example.minesweeper.service.persistence;

import com.example.minesweeper.domain.Game;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is a temporary solution before implementing a database or similar
 */
@Component
public class GameInMemoryPersistenceService {

    private Map<Integer, Game> storage = new HashMap<>();

    public Game saveGame(Game game) {
        if (StringUtils.isEmpty(game.getId())) {
            game.setId(this.generateRandomId());
        }
        this.storage.put(game.getId(), game);
        return game;
    }

    public Game getGameById(Integer id) {
        return this.storage.get(id);
    }

    private Integer generateRandomId() {
        final var random = new Random();
        Integer id;
        do {
            id = random.nextInt(100);
        } while (storage.get(id) != null);
        // TODO This line above is DANGEOUS, can enter in an infinite loop. Should be fixed
        return id;
    }

}