package org.iogame.model.fleet.ship.modules;

import org.iogame.model.data.EResource;
import org.iogame.model.data.EModule;
import org.iogame.model.data.dataModules;

import java.util.HashMap;


public abstract class GeneralModule {
    private final EModule emodule;
    private final String name;
    private final String description;
    private HashMap<EResource, Double> cost;
    private final int slot; // slot consumption for all modules except BodyModule, which provide slot
    private double duration;
    private final int quantity;

    public GeneralModule(EModule emodule, String name, String description,
                         HashMap<EResource, Double> cost, int slot, double duration, int quantity) {
        this.emodule = emodule;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.slot = slot;
        this.duration = duration;
        this.quantity = quantity;
    }



    // Use this function to create a module  // TODO: Move this to the Blueprint Editor and ShipBuilder; shouldn't be inside here!
    public static GeneralModule instantiateModule(EModule moduleEnum, int quantity){
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

    public HashMap<EResource, Double> getTotalCost(){
        HashMap<EResource, Double> totalCost = new HashMap<>();
        for(EResource res: this.cost.keySet()){
            totalCost.put(res, this.cost.get(res) * this.quantity);
        }
        return totalCost;
    };

    public int getSlot() {
        return slot;
    }

    public int getTotalSlot(){
        return this.slot * this.quantity;
    };

    public double getDuration() {
        return duration;
    }

    public double getTotalDuration() {
        return this.duration * this.quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
