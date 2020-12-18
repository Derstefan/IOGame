package org.iogame.model.fleet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.iogame.model.battle.Battle;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;



public class Fleet {


    private String name;
    private double x;
    private double y;
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
        this.x = location.getX();
        this.y = location.getY();
        this.speed = speed;
        this.location = location;
        this.player = player;
        location.getFleets().add(this);
    }

    //return true if arrived
    public boolean update(double delta) {
        if (movement != null) {
            double nextStep = Math.sqrt(Math.pow(dx * speed * delta, 2) + Math.pow(dy * speed * delta, 2));
            if (getTravelDistance() <= nextStep) {
                arrived();
                return true;
            }
            x = x + dx * speed * delta;
            y = y + dy * speed * delta;
            //System.out.println("x=" + x + " , y = " + y);
        }
        return false;
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

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Movement getMovement() {
        return movement;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
