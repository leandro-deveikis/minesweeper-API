package com.example.minesweeper.service.persistence;

import com.example.minesweeper.domain.Player;
import com.example.minesweeper.exception.MinesweeperException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class PlayerInMemoryPersistenceService {

    private Map<Integer, Player> storage = new HashMap<>();

    public Player savePlayer(Player player) {
        if (StringUtils.isEmpty(player.getId())) {
            player.setId(this.generateRandomId());
        }
        this.storage.put(player.getId(), player);
        return player;
    }

    public Player getPlayerById(Integer id) {
        Player player = this.storage.get(id);
        if (player == null)
            throw new MinesweeperException("Player not found: " + id, HttpStatus.NOT_FOUND);
        return player;
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
