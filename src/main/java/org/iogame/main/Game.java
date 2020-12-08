package org.iogame.main;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.Movement;
import org.iogame.model.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {

	private long lastTime;

	private List<Planet> planets = new ArrayList<>();
	private List<Fleet> fleets = new ArrayList<>();

	public Game() {
		Planet p = new Planet(4.0,7.0);
		Planet p2 = new Planet(2.0,6.0);
		planets.add(p);
		planets.add(p2);

		Fleet f = new Fleet(1,p);
		fleets.add(f);
		moveTo(f,p2);
		for(int i=0;i<100;i++) {
		loop(0.1);
		}
		lastTime= System.currentTimeMillis();
	}

	/*
	Threadfunction
	 */
	public void run() {
		long now = System.currentTimeMillis();
		double delta = (now - lastTime)/1000;
		lastTime=now;
		loop(delta);
	}

	/*
	Mainloop with delta as timediff in s to last call
	 */
	public void loop(double delta) {
		//Fleets
		for (Fleet f:fleets) {
			f.move(delta);
		}

		//Planets
		for(Planet p:planets){
			p.loop(delta);
		}
	}

	private void moveTo(Fleet fleet, Planet planet) {
		Movement movement = new Movement(fleet.getLocation(),planet);
		fleet.setMovement(movement);
	}

}
