package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class BodyMod extends GeneralModule {
    private double speed;
    private double agility;


    public BodyMod(EModule emodule, String name, String description,
                   HashMap<EResource, Double> cost, double duration, int quantity, int slot,
                   double speed, double agility) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.speed = speed;
        this.agility = agility;
    }

    // Getter
    public double getAgility() {
        return agility;
    }

    public double getSpeed() {
        return speed;
    }

    // Setter
    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
