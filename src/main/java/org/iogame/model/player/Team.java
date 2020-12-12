package org.iogame.model.player;

import java.util.List;

public class Team {

    private final String name;
    private final List<Player> players;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

}
