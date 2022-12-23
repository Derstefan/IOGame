package org.iogame.core;

import org.iogame.model.player.Player;
import org.iogame.model.player.Team;
import org.junit.jupiter.api.Test;

class GameObjectTest {

    @Test
    void shouldCreateGameObjectWithDefaultPositions() {
        var gameObject = new TestGameObject();
        assertEquals(0.0D, gameObject.getX());
        assertEquals(0.0D, gameObject.getY());
    }

    @Test
    void shouldComputeCorrectOwnership() {
        Team team = new Team("team");
        var p1 = new Player("p1", team);
        var p2 = new Player("p2", team);

        var gameObject = new TestGameObject();
        gameObject.setOwner(p1);

        assertTrue(gameObject.isOwnedBy(p1));
        assertFalse(gameObject.isOwnedBy(p2));
    }

    static class TestGameObject extends GameObject {
        @Override
        public void update(long delta) {

        }
    }

}