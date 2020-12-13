package org.iogame.model.fleet.ship;

import org.iogame.model.data.EResource;

import org.iogame.model.fleet.ship.modules.AttackMod;
import org.iogame.model.fleet.ship.modules.BodyMod;
import org.iogame.model.fleet.ship.modules.DefenseMod;
import org.iogame.model.fleet.ship.modules.SpecialMod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Ship {
    // general parameters
    private final String name;
    // private final String blueprintID = null;  // FIXME: No need, if blueprints managed by different class
    private final HashMap<EResource, Double> cost;
    private double buildingTime;
    private final int size; // number of Modules of instance BodyMod
    private final int slot; // available slots
    private double speed = 0.0;
    private double agility = 0.0;
    // special properties
    private List<String> bonus = new ArrayList<String>();
    private double cargoVolume = 0;
    // defense properties
    private double armor = 0.0;
    private double shield = 0.0;
    private double shieldRegeneration = 0.0;
    // attack properties;
    private double attack = 0.0;
    private ArrayList<String> damageType;
    // List of modules
    private List<Module> modules;

    // General Constructor to build a new ship type without a blueprint
    // TODO: Add Constructor to build ship from blueprint, which is a summary of the modules parameters
    //  according to Ship Class but without instatiating a Ship object; Later the first one should be removed,
    //  so that it is only possible to create a ship from a blueprint
    public Ship(String name, List<Module> modules){
        this.name = name;
        this.modules = modules;

     
        

    }
    // Getter
    public List<Module> getModulesOfType(String type){
        List<Module> returnList = new ArrayList<>();
        for (Module module: this.modules){   // fill modules HashMap
            if(type == "body" && BodyMod.class.isInstance(module)) {
                returnList.add(module);
            } else if(type == "body" && SpecialMod.class.isInstance(module)){
                returnList.add(module);
            } else if(type == "body" && DefenseMod.class.isInstance(module)){
                returnList.add(module);
            } else if(type == "body" && AttackMod.class.isInstance(module)){
                returnList.add(module);
            } else {
                System.out.println("Module " +  module.toString() + " is not assigned to any type of " +
                        "{\"body\", \"special\", \"defense\", \"attack\"}.");
            }
        }
        return returnList;
    }

}
