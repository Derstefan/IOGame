package src.main.java.org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import src.main.java.org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class BodyMod extends Module{
    private double agility;


    public BodyMod(EModule emodule, String name, String description,
                   HashMap<EResource, Double> cost, double duration, int quantity, int slot,
                   double agility) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.agility = agility;
    }

    // Getter
    public double getAgility() {
        return agility;
    }

    // Setter
    public void setAgility(double agility) {
        this.agility = agility;
    }
}
