package org.iogame.model.planet.buildings;

import org.iogame.model.enums.EBuilding;
import org.iogame.model.enums.EResource;
import org.iogame.model.planet.Planet;

import java.util.HashMap;

public abstract class Building {
    private final EBuilding type;
    private int lvl;

    public static Building getInstance(EBuilding type) {
        if (type.equals(EBuilding.LITHIUMMINE)) {
            return new LithiumMine();
        }
        return null;
    }

    public static HashMap<EResource, Double> getCosts(EBuilding type) {

        return null;
    }

    public static double getDuration(EBuilding type) {
        return 5.0;
    }

    public Building(EBuilding type) {
        this.type = type;
    }

    public abstract void updateBuilding(Planet planet);

    public abstract void destroyBuilding(Planet planet);

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


}
