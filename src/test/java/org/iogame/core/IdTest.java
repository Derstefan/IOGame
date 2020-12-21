package org.iogame.core;

import org.iogame.StaticSettings;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.iogame.StaticSettings.ENV;
import static org.junit.jupiter.api.Assertions.*;

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
    void testFromString() {
        Id devId = Id.fromString("42");
        assertEquals(Long.valueOf(42), devId.getId());
    }

    @Test
    void sameIdsForDifferentClasses() {
        Id planetId1 = Id.generateForClass(Planet.class);
        Id planetId2 = Id.generateForClass(Planet.class);
        Id fleetId = Id.generateForClass(Fleet.class);

        assertEquals(0L, planetId1.<Long>getId());
        assertEquals(1L, planetId2.<Long>getId());
        assertEquals(0L, fleetId.<Long>getId());
    }

    @Test
    void shouldBeEqualInMapGet() {
        Id id1 = new Id(42);
        Id id2 = new Id(42);
        Map<Id, String> map = Map.of(id1, "foo");

        assertEquals("foo", map.get(id2));
    }
}