package org.iogame.main;

import org.iogame.model.GameObject;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.FleetFactory;
import org.iogame.model.planet.Planet;
import org.iogame.model.planet.PlanetFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Map<Class<? extends GameObject>, List<GameObject>> gameObjects;
    private final FleetFactory fleetFactory;
    private final PlanetFactory planetFactory;

    public Game() {
        this.gameObjects = new HashMap<>();
        this.fleetFactory = new FleetFactory(this);
        this.planetFactory = new PlanetFactory(this);
    }

    public void run(double delta) {
        for (List<? extends GameObject> value : gameObjects.values()) {
            for (GameObject gameObject : value) {
                gameObject.update(delta);
            }
        }
    }

    public void addGameObject(GameObject gameObject) {
        List<GameObject> gameObjectList = this.gameObjects.computeIfAbsent(gameObject.getClass(), ignore -> new ArrayList<>());
        gameObjectList.add(gameObject);
    }

    public List<Planet> getPlanets() {
        return getUnchecked(Planet.class);
    }

    public List<Fleet> getFleets() {
        return getUnchecked(Fleet.class);
    }

    public PlanetFactory getPlanetFactory() {
        return planetFactory;
    }

    public FleetFactory getFleetFactory() {
        return fleetFactory;
    }

    private <T extends GameObject> List<T> getUnchecked(Class<T> clazz) {
        List<T> castObjects = new ArrayList<>();
        List<GameObject> gameObjects = this.gameObjects.get(clazz);
        gameObjects.forEach(gameObject -> castObjects.add((T) gameObject));
        return castObjects;
    }
}
