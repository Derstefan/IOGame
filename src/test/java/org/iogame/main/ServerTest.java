package org.iogame.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    Server server;

    @BeforeEach
    void setup() {
        this.server = Server.getInstance();
    }

    @Test
    void shouldAddANewGame() {
        assertEquals(0, server.getGames().size());
        server.createGame("test");
        assertEquals(1, server.getGames().size());
    }

    @Test
    void shouldGetGamesById() {
        UUID gameId1 = server.createGame("test1");
        UUID gameId2 = server.createGame("test2");

        assertEquals("test1", server.getGameById(gameId1).gameName());
        assertEquals("test2", server.getGameById(gameId2).gameName());
    }
}