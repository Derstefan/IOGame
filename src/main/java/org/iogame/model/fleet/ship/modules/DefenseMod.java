package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class DefenseMod extends GeneralModule {
    private final String defenseType;
    private double hitPoints;
    private double regeneration;
    public DefenseMod(EModule emodule, String name, String description, HashMap<EResource, Double> cost, int slot,
                      double duration, int quantity, String defenseType, double hitPoints, double regeneration) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.defenseType = defenseType;
        this.hitPoints = hitPoints;
        this.regeneration = regeneration;
    }

    // Getter
    public double getHitPoints() {
        return this.hitPoints;
    }

    public double getTotalHitPoints() {
        return this.hitPoints * this.getQuantity();
    }

    public abstract double getShield();

    public abstract double getTotalShield();

    public abstract double getArmor();

    public abstract double getTotalArmor();

    public double getRegeneration() {
        return regeneration;
    }

    public String getDefenseType() {
        return defenseType;
    }

    // Setter
    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setRegeneration(double regeneration) {
        this.regeneration = regeneration;
    }



}

