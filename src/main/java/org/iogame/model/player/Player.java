package org.iogame.model.player;

import org.iogame.core.Id;

public class Player {

    private final Id id;

    private final String name;
    private Team team;

    public Player(String name, Team team) {
        this.id = Id.generateForClass(Player.class);

        this.name = name;
        this.team = team;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Player)) return false;
        return this.id.equals(((Player) obj).id);
    }
}
