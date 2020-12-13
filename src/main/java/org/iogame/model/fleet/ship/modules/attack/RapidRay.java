package org.iogame.model.fleet.ship.modules.attack;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;
import org.iogame.model.fleet.ship.modules.AttackMod;

import java.util.HashMap;

public class RapidRay extends AttackMod {
    public RapidRay(EModule emodule, String name, String description, HashMap<EResource, Double> cost, int slot,
                    double duration, int quantity,
                    String damageType, int recharge, double damage, double accuracy) {
        super(emodule, name, description, cost, slot,duration,
                quantity, damageType, recharge, damage, accuracy);
    }
}
