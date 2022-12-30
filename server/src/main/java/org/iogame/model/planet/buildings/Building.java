package org.iogame.model.planet.buildings;

import org.iogame.core.GameObject;
import org.iogame.model.planet.Planet;

public abstract class Building extends GameObject {
    private final EBuilding type;
    private int lvl;


    public Building(EBuilding type) {
        this.type = type;
        this.lvl = 0;
    }

    public EBuilding getType() {
        return type;
    }

    public void lvlUp() {
        lvl++;
    }

    public void lvlDown() {
        lvl--;
    }

    public int getLvl() {
        return lvl;
    }

    public abstract void activate(Planet planet);


    @Override
    public void update(long delta) {

    }
}
