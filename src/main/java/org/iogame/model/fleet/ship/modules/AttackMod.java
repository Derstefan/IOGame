package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class AttackMod extends Module{
    private final String attackType;
    private final int recharge;
    private double attackRate;
    public AttackMod(EModule emodule, String name, String description, HashMap<EResource, Double> cost,
                     int slot, double duration, int quantity,
                     String attackType, int recharge, double attackRate) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.attackType = attackType;
        this.recharge = recharge;
        this.attackRate = attackRate;

    }
}
