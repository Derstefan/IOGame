package org.iogame.model.planet;

import org.iogame.exceptions.NotBuildableException;
import org.iogame.model.data.EBuilding;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void shouldCreatePlanet() {
        Planet planet = new Planet(0.0D, 0.2D);
        assertNotNull(planet);
        assertEquals(0.0D, planet.getX());
        assertEquals(0.2D, planet.getY());
    }

    @Test
    void shouldBuildLithuimMine() {
        Planet planet = new Planet(0.0D, 0.2D);
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
        try {
            planet.build(EBuilding.LITHIUMMINE);
        } catch (NotBuildableException e) {
        }
        assertEquals(1,planet.getBuildingManager().getBuildingQueue().size());
        assertEquals(0,planet.getBuildingManager().getLvlOf(EBuilding.LITHIUMMINE));
        planet.update(1000);
        planet.update(1000);
        planet.update(1000);
        planet.update(2000);
        assertEquals(1,planet.getBuildingManager().getLvlOf(EBuilding.LITHIUMMINE));
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
        try {
            planet.build(EBuilding.LITHIUMMINE);
        } catch (NotBuildableException e) {
        }
        assertEquals(1,planet.getBuildingManager().getBuildingQueue().size());
        planet.cancelBuilding(0);
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
    }
}