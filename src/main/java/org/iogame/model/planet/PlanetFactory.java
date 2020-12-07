package org.iogame.model.planet;

import org.iogame.main.Game;
import org.iogame.model.GameObjectFactory;

import java.util.concurrent.atomic.AtomicLong;

public class PlanetFactory extends GameObjectFactory<Planet> {

    private static final String PLANET_NAME = "Fleet";
    private static final AtomicLong id = new AtomicLong(0L);

    public PlanetFactory(Game game) {
        super(game);
    }

    public Planet create(double x, double y) {
        Planet planet = new Planet(String.format("%s_%d", PLANET_NAME, id.getAndIncrement()), x, y);
        game.addGameObject(planet);
        return planet;
    }
}
