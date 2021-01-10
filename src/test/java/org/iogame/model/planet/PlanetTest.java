package org.iogame.model.planet;

import org.iogame.exceptions.NotBuildableException;
import org.iogame.model.planet.buildings.EBuilding;
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
    void shouldBuildClasterMine() {
        Planet planet = new Planet(0.0D, 0.2D);
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
        System.out.println("before: " + planet.getStorage().stockToString());
        try {
            planet.build(EBuilding.CLASTER_MINE);
        } catch (NotBuildableException e) {
            System.out.println("didnt't start....");
        }

        assertEquals(1,planet.getBuildingManager().getBuildingQueue().size());
        assertEquals(0,planet.getBuildingManager().getLvlOf(EBuilding.CLASTER_MINE));

        planet.update(1000);
        planet.update(1000);
        planet.update(1000);
        planet.update(3000);
        System.out.println("after: " + planet.getStorage().stockToString());
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
        assertEquals(1,planet.getBuildingManager().getLvlOf(EBuilding.CLASTER_MINE));


    }

    @Test
    void shouldCancelQueueProcess(){
        Planet planet = new Planet(0.0D, 0.2D);
        try {
            planet.build(EBuilding.CLASTER_MINE);
        } catch (NotBuildableException e) {
        }
        assertEquals(1,planet.getBuildingManager().getBuildingQueue().size());
        planet.cancelBuilding(0);
        assertEquals(0,planet.getBuildingManager().getBuildingQueue().size());
    }
}