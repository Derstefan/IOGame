package org.iogame.main;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.Movement;
import org.iogame.model.fleet.ship.BlueprintManager;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;
import org.iogame.model.player.Team;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {

    private static final int MAX_PLAYERS = 5;
    private final String name;

    private long lastTime;

    private List<Planet> planets = new ArrayList<>();
    private List<Fleet> fleets = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private static final BlueprintManager blueprintManager = new BlueprintManager();
    //private List<Movement> movements = new ArrayList<>();
    //private List<Battle> battles = new ArrayList<>();

    private boolean stopped = false;
    private boolean paused = false;


    public Game(String name) {
        this.name = name;
        Team t1 = new Team("rot");
        Team t2 = new Team("blau");

        Player tilman = new Player("tilman",t1);
        Player gerardo = new Player("gerardo",t2);



        Planet p1 = new Planet(4.0, 7.0);
        Planet p2 = new Planet(2.0, 6.0);
        planets.add(p1);
        planets.add(p2);

        Fleet f1 = new Fleet(0.001, p1, tilman);
        Fleet f2 = new Fleet(0.001, p2, gerardo);
        fleets.add(f1);
        fleets.add(f2);


        moveTo(f1, p2);
/*		for(int i=0;i<100;i++) {
		update(0.1);
		}*/
        lastTime = System.currentTimeMillis();
    }

    /*
    Threadfunction
     */
    public void run() {
        try {
            long now = System.currentTimeMillis();
            long delta = (now - lastTime);
            lastTime = now;
            System.out.println(delta);
            if (!paused) {
                update(delta);
            }
            Thread.sleep(200);
            if (!stopped) {
                run();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("game over");
    }

    /*
    Mainloop with delta as timediff (in s) to last call
     */
    public void update(long delta) {

        //Fleets
        for (Fleet f : fleets) {
            f.update(delta);
        }

        //Planets
        for (Planet p : planets) {
            p.update(delta);
            if (p.getBattle() != null) {
                //Battles
                p.getBattle().update(delta);
            }
        }

    }

    //Controllerfunctions
    public void moveTo(Fleet fleet, Planet planet) {
        Movement movement = new Movement(fleet.getLocation(), planet);
        fleet.setMovement(movement);
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

    // Getter

    public static BlueprintManager getBlueprintManager() {
        return blueprintManager;
    }
}
