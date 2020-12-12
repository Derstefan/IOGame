package src.main.java.org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import src.main.java.org.iogame.model.data.EModule;

import java.util.HashMap;

public abstract class SpecialMod extends Module{
    private final String bonus;
    private final int max;
    private final double cargoVolume;

    public SpecialMod(EModule emodule, String name, String description, HashMap<EResource, Double> cost, int slot,
                      double duration, int quantity,
                      String bonus, int max, double cargoVolume) {
        super(emodule, name, description, cost, slot,duration, quantity);
        this.bonus = bonus;
        this.max = max;
        this.cargoVolume = cargoVolume;
    }

    // Getter
    public String getBonus() {
        return bonus;
    }

    public int getMax() {
        return max;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }
}
