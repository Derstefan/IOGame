package org.iogame.model.fleet;

import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;
import org.iogame.model.player.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FleetTest {

    @Test
    void shouldCreateFleet() {
        Player player = new Player("p1",new Team("rot"));
        Planet location = new Planet(0.0D, 0.0D);
        Fleet fleet = new Fleet(0.5D, location,player);
        assertNotNull(fleet);
        assertEquals(0.5D, fleet.getSpeed());
        assertEquals(location, fleet.getLocation());
    }

}