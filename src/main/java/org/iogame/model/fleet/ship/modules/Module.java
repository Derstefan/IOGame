package src.main.java.org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import src.main.java.org.iogame.model.data.EModule;
import src.main.java.org.iogame.model.data.dataModules;

import java.util.HashMap;


public abstract class Module {
    private final EModule emodule;
    private final String name; //TODO: Implement Lookup
    private final String description; //TODO: Implement Lookup
    private HashMap<EResource, Double> cost;
    private final int slot; // slot consumption for all modules except BodyModule, which provide slot
    private double duration;
    private final int quantity;

    public Module(EModule emodule, String name, String description,
                  HashMap<EResource, Double> cost, int slot, double duration, int quantity) {
        this.emodule = emodule;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.slot = slot;
        this.duration = duration;
        this.quantity = quantity;
    }



    // Use this function to create a module
    public static Module instantiateModule(EModule moduleEnum, int quantity){
        return dataModules.instantiateModule(moduleEnum, quantity);
    }

    // Getter
    public EModule getEmodule() {
        return emodule;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<EResource, Double> getCost() {
        return cost;
    }

    public int getSlot() {
        return slot;
    }

    public double getDuration() {
        return duration;
    }

    public int getQuantity() {
        return quantity;
    }
}
