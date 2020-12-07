package org.iogame.main;

import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.Movement;
import org.iogame.model.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class Game {

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
		
	}

	public void loop(double delta) {
		for(int i=0;i<fleets.size();i++) {
			fleets.get(i).move(delta);
		}
	}

	private void moveTo(Fleet fleet, Planet planet) {
		Movement movement = new Movement(fleet.getLocation(),planet);
		fleet.setMovement(movement);
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public List<Fleet> getFleets() {
		return fleets;
	}

	public void setFleets(List<Fleet> fleets) {
		this.fleets = fleets;
	}

}
