package org.iogame.model.fleet;

import org.iogame.model.planet.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    @Test
    void shouldCreateFleet() {
        Planet location = new Planet(0.0D, 0.0D);
        Fleet fleet = new Fleet(0.5D, location);
        assertNotNull(fleet);
        assertEquals(0.5D, fleet.getSpeed());
        assertEquals(location, fleet.getLocation());
    }

}