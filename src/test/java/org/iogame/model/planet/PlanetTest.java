package org.iogame.model.planet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void shouldCreatePlanet() {
        Planet planet = new Planet("p", 0.0D, 0.2D);
        assertNotNull(planet);
        assertEquals("p", planet.getName());
        assertEquals(0.0D, planet.getX());
        assertEquals(0.2D, planet.getY());
    }

}