package org.iogame.model.fleet;

import org.iogame.main.Game;
import org.iogame.model.GameObjectFactory;
import org.iogame.model.planet.Planet;

import java.util.concurrent.atomic.AtomicLong;

public class FleetFactory extends GameObjectFactory<Fleet> {

    private static final String FLEET_NAME = "Fleet";
    private static AtomicLong id = new AtomicLong(0L);

    public FleetFactory(Game game) {
        super(game);
    }

    public Fleet create(double speed, Planet planet) {
        Fleet fleet = new Fleet(String.format("%s_%d", FLEET_NAME, id.getAndIncrement()), speed, planet);
        game.addGameObject(fleet);
        return fleet;
    }
}
