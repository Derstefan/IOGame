package org.iogame.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Playground {
    Game game;

    @BeforeEach
    void setup() {
        this.game = new Game("test");
    }

    @AfterEach
    void stopGame() {
        this.game.interrupt();
        this.game = null;
    }

    @Test
    void playground() {
    }
}
