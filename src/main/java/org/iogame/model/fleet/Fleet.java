package org.iogame.model.fleet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.iogame.core.GameObject;
import org.iogame.model.battle.Battle;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;



public class Fleet extends GameObject {

    private double dx;
    private double dy;
    private double speed;
    private boolean returned = false;
    @JsonIgnore
    private Movement movement = null;
@JsonIgnore
    private Planet location;

    private Player player;

    public Fleet(double speed, Planet location, Player player) {
        super(location.getX(), location.getY());
        this.speed = speed;
        this.location = location;
        this.player = player;
        location.getFleets().add(this);
    }

    @Override
    public void update(long delta) {
        if (movement != null) {
            double nextStep = Math.sqrt(Math.pow(dx * speed * delta, 2) + Math.pow(dy * speed * delta, 2));
            if (getTravelDistance() <= nextStep) {
                arrived();
            }
            x = x + dx * speed * delta;
            y = y + dy * speed * delta;
            //System.out.println("x=" + x + " , y = " + y);
        }
    }

    public void arrived() {

        location = movement.getTarget();
        x = location.getX();
        y = location.getY();
        dx=0;
        dy=0;
        location.getFleets().add(this);
        if(!location.checkPeace() && location.getBattle()==null){
            location.setBattle(new Battle(this.location));
            System.out.println("here??");
        }
        movement = null;

    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }


    public Planet getLocation() {
        return location;
    }

    public void moveTo(Planet planet) {
        Movement movement = new Movement(getLocation(), planet);
        setMovement(movement);
    }

    public double getSpeed() {
        return speed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
        double x1 = movement.getOrigin().getX();
        double y1 = movement.getOrigin().getY();
        double x2 = movement.getTarget().getX();
        double y2 = movement.getTarget().getY();

        double length = getTravelDistance();
        dx = (x2 - x1) / length;
        dy = (y2 - y1) / length;
        //System.out.println("dx=" + dx + " , dy = " + dy);
        location.getFleets().remove(this);
        location = null;

    }

    private double getTravelDistance() {
        double x2 = movement.getTarget().getX();
        double y2 = movement.getTarget().getY();

        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));

    }
}
