package org.iogame.model.player;

import org.iogame.model.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Planet> planets;
    private final String name;
    private Team team;

    public Player(String name) {
        this.name = name;
        this.planets = new ArrayList<>();
    }
}
