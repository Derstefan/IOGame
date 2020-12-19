package org.iogame.core;

import org.iogame.StaticSettings;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.iogame.StaticSettings.ENV;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdTest {

    @BeforeEach
    void setup() {
        ENV = StaticSettings.Environment.DEVELOPMENT;
    }

    @Test
    void shouldComputeEqualsBaseOnEnviroment() {
        Id devId = Id.generate();
        ENV = StaticSettings.Environment.PRODUCTION;
        Id prodId = Id.generate();

        assertNotEquals(prodId, devId);
        assertEquals(devId, devId);
        assertEquals(prodId, prodId);
    }

    @Test
    void testToString() {
        Id devId = Id.fromString("42");
        assertEquals(Long.valueOf(42), devId.getValue());
    }

    @Test
    void sameIdsForDifferentClasses() {
        Id planetId1 = Id.generateForClass(Planet.class);
        Id planetId2 = Id.generateForClass(Planet.class);
        Id fleetId = Id.generateForClass(Fleet.class);

        assertEquals(0L, planetId1.<Long>getValue());
        assertEquals(1L, planetId2.<Long>getValue());
        assertEquals(0L, fleetId.<Long>getValue());
    }
}