package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class AttackMod extends Module{
    private final String damageType; // TODO: Which damage Type? Ideas are "armor", "shield", "splash", ...
    private final int recharge;
    private double damage;
    public AttackMod(EModule emodule, String name, String description, HashMap<EResource, Double> cost,
                     int slot, double duration, int quantity,
                     String damageType, int recharge, double damage) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.damageType = damageType;
        this.recharge = recharge;
        this.damage = damage;

    }
}
