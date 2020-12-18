package org.iogame.main;

import org.iogame.model.battle.Battle;
import org.iogame.model.GameObject;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;
import org.iogame.model.player.Team;

import java.util.*;

public class Game extends Thread {

    private static final int MAX_PLAYERS = 5;
    private final String name;

    private final Map<Class<? extends GameObject>, Map<UUID, GameObject>> gameObjects;

    private final List<Player> players;
    private final List<Team> teams;

    private boolean stopped = false;
    private boolean paused = false;

    public Game(String name) {
        this.name = name;
        this.gameObjects = new HashMap<>();
        this.players = new LinkedList<>();
        this.teams = new LinkedList<>();
        debug();
    }

    public void debug() {
        Team t1 = new Team("rot");
        Team t2 = new Team("blau");
        teams.add(t1);
        teams.add(t2);

        Player tilman = new Player("tilman",t1);
        Player gerardo = new Player("gerardo",t2);

        Planet p1 = createPlanet(4.0, 7.0);
        Planet p2 = createPlanet(2.0, 6.0);

        Fleet f1 = createFleet(0.001, p1, tilman);
        Fleet f2 = createFleet(0.001, p2, gerardo);

        getFleetById(f1.getId()).moveTo(p2);
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!stopped) {
            try {
                long now = System.currentTimeMillis();
                long delta = (now - lastTime);
                lastTime = now;
                if (!paused) {
                    update(delta);
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("game over");
    }

    /**
     * Update method with delta as time difference (in s) to last call.
     * This method will be called for every frame in the main loop.
     */
    public void update(long delta) {
        for (Map<UUID, GameObject> innerMap : gameObjects.values()) {
            for (GameObject gameObject : innerMap.values()) {
                gameObject.update(delta);
            }
        }
    }

    public void join(Player player) throws IllegalArgumentException {
        if (players.size() < MAX_PLAYERS) {
            this.players.add(player);
        }
        throw new IllegalArgumentException("Maximum number of players reached.");
    }

    public void leave(Player player) {
        players.remove(player);
    }

    public String gameName() {
        return this.name;
    }

    public Planet createPlanet(double x, double y) {
        Planet planet = new Planet(x, y);
        addGameObject(planet);
        return planet;
    }

    public Fleet createFleet(double speed, Planet planet, Player player) {
        Fleet fleet = new Fleet(speed, planet, player);
        addGameObject(fleet);
        return fleet;
    }

    public void addGameObject(GameObject gameObject) {
        Map<UUID, GameObject> gameObjectList = this.gameObjects.computeIfAbsent(gameObject.getClass(), ignore -> new IdentityHashMap<>());
        gameObjectList.put(gameObject.getId(), gameObject);
    }

    public List<Planet> getPlanets() {
        return getUnchecked(Planet.class);
    }

    public List<Fleet> getFleets() {
        return getUnchecked(Fleet.class);
    }

    public Planet getPlanetById(UUID id) {
        return (Planet) this.gameObjects.get(Planet.class).get(id);
    }

    public Fleet getFleetById(UUID id) {
        return (Fleet) this.gameObjects.get(Fleet.class).get(id);
    }

    private <T extends GameObject> List<T> getUnchecked(Class<T> clazz) {
        List<T> castObjects = new ArrayList<>();
        Collection<GameObject> gameObjects = this.gameObjects.get(clazz).values();
        gameObjects.forEach(gameObject -> castObjects.add((T) gameObject));
        return castObjects;
    }

    public List<Battle> getBattles() {
        List<Battle> battles = new ArrayList<>();
        for (Planet p : planets) {
            if(p.getBattle()!=null) {
                battles.add(p.getBattle());
            }
        }
        return battles;
    }
}
