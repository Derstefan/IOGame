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
}
