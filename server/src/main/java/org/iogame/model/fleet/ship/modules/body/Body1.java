package org.iogame.model.fleet.ship.modules.body;

import org.iogame.model.data.EModule;
import org.iogame.model.data.EResource;
import org.iogame.model.fleet.ship.modules.BodyMod;

import java.util.HashMap;

public class Body1 extends BodyMod {
    public Body1(EModule emodule, String name, String description,
                 HashMap<EResource, Double> cost, double duration, int quantity,
                 int slot, double agility) {
        super(emodule, name, description, cost, duration, quantity, slot, agility);
    }

}
