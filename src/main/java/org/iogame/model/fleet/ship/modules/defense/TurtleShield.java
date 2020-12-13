package org.iogame.model.fleet.ship.modules.defense;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;
import org.iogame.model.fleet.ship.modules.DefenseMod;

import java.util.HashMap;

public class TurtleShield extends DefenseMod{
    public TurtleShield(EModule emodule, String name, String description, HashMap<EResource, Double> cost, int slot,
                        double duration, int quantity, String type, double hitPoints, double regeneration) {
        super(emodule, name, description, cost, slot,duration,
                quantity, type, hitPoints, regeneration);
    }

    @Override
    public double getShield() {
        if(this.getDefenseType().equals("shield")){
            return this.getHitPoints();
        } else {return 0.0;}
    }

    @Override
    public double getTotalShield() {
        if(this.getDefenseType().equals("shield")){
            return this.getTotalHitPoints();
        } else {return 0.0;}
    }

    @Override
    public double getArmor() {
        if(this.getDefenseType().equals("armor")){
            return this.getHitPoints();
        } else {return 0.0;}
    }

    @Override
    public double getTotalArmor() {
        if(this.getDefenseType().equals("armor")){
            return this.getTotalHitPoints();
        } else {return 0.0;}
    }
}
