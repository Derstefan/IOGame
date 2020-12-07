package org.iogame.main;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.FleetFactory;
import org.iogame.model.planet.Planet;
import org.iogame.model.planet.PlanetFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameTest {

    Game game;

    @BeforeEach
    void setup() {
        this.game = new Game();
    }

    @Test
    void shouldCreateAndGetGameObjects() {
        PlanetFactory planetFactory = game.getPlanetFactory();
        FleetFactory fleetFactory = game.getFleetFactory();

        Planet planet1 = planetFactory.create(1.0D, 2.0D);
        Planet planet2 = planetFactory.create(5.0D, 7.0D);

        Fleet fleet1 = fleetFactory.create(1.0D, planet1);
        Fleet fleet2 = fleetFactory.create(2.0D, planet2);

        assertThat(game.getFleets().toArray()).containsExactlyInAnyOrder(List.of(fleet1, fleet2).toArray());
        assertThat(game.getPlanets().toArray()).containsExactlyInAnyOrder(List.of(planet1, planet2).toArray());
    }

}