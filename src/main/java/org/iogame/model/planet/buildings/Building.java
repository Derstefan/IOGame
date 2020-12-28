package org.iogame.model.planet.buildings;

import org.iogame.core.GameObject;
import org.iogame.model.data.EBuilding;
import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;

import java.util.HashMap;

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

    public void lvlUp(Planet planet) {
        lvl++;
        planet.updateStats();
    }

    public void lvlDown(Planet planet) {
        lvl--;
        planet.updateStats();
    }

    public int getLvl() {
        return lvl;
    }

    public abstract void updateValues(Planet planet);

    public static Building getInstance(EBuilding type){
        if(type.equals(EBuilding.LITHIUMMINE)){
            return new LithiumMine();
        } else {
            return null;
        }

    }


    @Override
    public void update(long delta) {

    }
}
