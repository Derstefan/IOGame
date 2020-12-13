package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class AttackMod extends GeneralModule {
    private final String damageType; // TODO: Which damage Type? Ideas are "armor", "shield", "splash", ...
    private final int recharge;
    private double damage;
    private final double basicAccuracy;
    private double accuracy;
    public AttackMod(EModule emodule, String name, String description, HashMap<EResource, Double> cost,
                     int slot, double duration, int quantity,
                     String damageType, int recharge, double damage, double basicAccuracy) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.damageType = damageType;
        this.recharge = recharge;
        this.damage = damage;
        this.basicAccuracy = basicAccuracy;
        this.accuracy = basicAccuracy;
    }

    // Getter
    public String getDamageType() {
        return damageType;
    }

    public int getRecharge() {
        return recharge;
    }

    public double getDamage() {
        return damage;
    }

    public double getTotalDamage() {
        return this.damage * this.getQuantity();
    }

    public double getBasicAccuracy() {
        return basicAccuracy;
    }

    public double getAccuracy() {
        return accuracy;
    }

    // Setter
    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
