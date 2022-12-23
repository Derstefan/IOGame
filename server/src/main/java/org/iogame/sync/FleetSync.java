package org.iogame.sync;

import org.iogame.model.fleet.Fleet;

public class FleetSync {

    private long id;

    private float x;
    private float y;

    //not normalised
    private float vx;
    private float vy;

    private long timeOfPosition;

    public FleetSync(Fleet fleet) {
        //this.id = fleet.getId();
        this.x=(float)fleet.getX();
        this.y=(float)fleet.getY();
        this.timeOfPosition=System.currentTimeMillis();
        this.vx=(float)fleet.getDx();
        this.vy=(float)fleet.getDy();
    }

    public long getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public long getTimeOfPosition() {
        return timeOfPosition;
    }

    public void setTimeOfPosition(long timeOfPosition) {
        this.timeOfPosition = timeOfPosition;
    }
}
