package com.example.minesweeper.domain;

/**
 * State of a Game. If it's finished, no other changes can be made.
 */
public enum GameState {
    PLAYING,
    FINISHED
}
