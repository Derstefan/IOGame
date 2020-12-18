package org.iogame.main;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;
import org.iogame.model.player.Team;
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
        Team t1 = new Team("rot");
        Team t2 = new Team("blau");

        Player tilman = new Player("tilman",t1);
        Player gerardo = new Player("gerardo",t2);

        Planet p1 = game.createPlanet(4.0, 7.0);
        Planet p2 = game.createPlanet(2.0, 6.0);

        Fleet f1 = game.createFleet(0.001, p1, tilman);
        Fleet f2 = game.createFleet(0.001, p2, gerardo);

        game.getFleetById(f1.getId()).moveTo(p2);
    }
}
