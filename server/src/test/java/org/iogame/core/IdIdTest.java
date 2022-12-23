package org.iogame.core;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdIdTest extends BaseIdTest {

    @Test
    void testFromString() {
        Id id = Id.fromString("42");
        assertEquals(Long.valueOf(42), id.getId());

        Id generatedId = Id.generateForClass(IdIdTest.class);
        Id fromStringId = Id.fromString("0");
        assertEquals(generatedId, fromStringId);
    }

    @Test
    void sameIdsForDifferentClasses() {
        Id planetId1 = Id.generateForClass(Planet.class);
        Id planetId2 = Id.generateForClass(Planet.class);
        Id fleetId = Id.generateForClass(Fleet.class);

        assertEquals(0L, planetId1.getId());
        assertEquals(1L, planetId2.getId());
        assertEquals(0L, fleetId.getId());
    }

    @Test
    void shouldBeEqualInImmutableMapGet() {
        Id generatedId = Id.generateForClass(IdIdTest.class);
        Id fromStringId = Id.fromString("0");
        var map = Map.of(generatedId, "foo");

        assertEquals("foo", map.get(fromStringId));
    }

    @Test
    void shouldBeEqualInHashMapGet() {
        Id generatedId = Id.generateForClass(IdIdTest.class);
        Id fromStringId = Id.fromString("0");
        Map<Id, String> map = new HashMap<>();
        map.put(generatedId, "foo");

        assertEquals("foo", map.get(fromStringId));
    }
}