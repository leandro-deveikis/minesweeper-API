package com.example.minesweeper.controller;

import com.example.minesweeper.domain.Player;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private static final Log LOGGER = LogFactory.getLog(PlayerController.class);

    @GetMapping("/{id}")
    @ResponseBody
    public Player getPlayer(@PathVariable() String id) {
        LOGGER.info("GET player by id called: " + id);
        // TODO complete this service
        return null;
    }
}
