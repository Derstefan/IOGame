package model.ship.modules;

import java.util.HashMap;
import model.enums.EResource;
import model.planet.Resource;

public abstract class Module {
    private final E_Module module;
    private final String name; //TODO: Implement Lookup
    private final String description; //TODO: Implement Lookup
    private final int quantity;
    private HashMap<EResource, Double> costs;
    private double duration;

    public Module(E_Module module, String name, String description, HashMap<EResource, Double> costs, double duration) {
        this.module = module;
        this.name = name;
        this.description = description;
        this.costs = costs;
        this.duration = duration;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<EResource, Double> getCosts() {
        return costs;
    }

    public double getDuration() {
        return duration;
    }
}
