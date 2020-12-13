package org.iogame.model.player;

import org.iogame.model.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Planet> planets;
    private final String name;
    private Team team;


    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
  this.planets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
