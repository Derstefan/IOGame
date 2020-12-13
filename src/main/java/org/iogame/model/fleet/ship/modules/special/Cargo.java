package org.iogame.model.fleet.ship.modules.special;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;
import org.iogame.model.fleet.ship.modules.SpecialMod;

import java.util.HashMap;

public class Cargo extends SpecialMod {
    public Cargo(EModule emodule, String name, String description, HashMap<EResource, Double> cost, int slot,
                 double duration, int quantity, String bonus, int max, double cargoVolume) {
        super(emodule, name, description, cost, slot, duration,
                quantity, bonus, max, cargoVolume);
    }
}
