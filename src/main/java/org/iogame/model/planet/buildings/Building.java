package org.iogame.model.planet.buildings;


import org.iogame.model.enums.EResource;

import java.util.HashMap;

public abstract class Building {
    private double duration;
    private HashMap<EResource, Double> costs;
    //private int lvl;

    public abstract void updateBuilding();

    public abstract void destroyBuilding();

    public double getDuration() {
        return duration;
    }

    public HashMap<EResource, Double> getCosts() {
        return costs;
    }
}
