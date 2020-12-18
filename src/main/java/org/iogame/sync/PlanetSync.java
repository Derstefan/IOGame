package org.iogame.sync;

import org.iogame.model.planet.Planet;

public class PlanetSync {

    private long Id;

    private float x;
    private float y;

    public PlanetSync(Planet planet) {
        //this.id = planet.getId();
        this.x=(float)planet.getX();
        this.y=(float)planet.getY();
    }

    public long getId() {
        return Id;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
