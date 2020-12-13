package org.iogame.model.player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Player> planets;
    private String name;
    private Team team;


    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.planets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
