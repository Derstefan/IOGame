package org.iogame.model.fleet.ship;

import org.iogame.model.data.EResource;

import org.iogame.model.fleet.ship.modules.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Ship {
    // general parameters
    private final String name;
    private final String id; // EBlueprint.getId()
    private HashMap<EResource, Double> cost;
    private double buildingTime;
    // body properties
    private int size; // number of Modules of instance BodyMod
    private int slot; // available slots
    private double speed;
    private double agility;
    // special properties
    private List<String> bonus;
    private double cargoVolume;
    // defense properties
    private double hitpoints;
    private double armor;
    private double shield;
    // attack properties;
    private double damage;
    private ArrayList<String> damageType;
    // List of modules
    private List<GeneralModule> modules;

    // Constructor with Blueprint as input
    // TODO: if i pass blueprint to the constructor and set this.modules = blueprint.getModules(),
    //  are the the IDENTICAL objects or a copy therof?!
    public Ship(String name, Blueprint blueprint){
        this.name = name;
        this.id = blueprint.getId();
    }
    // Getter
    public List<GeneralModule> getModulesOfType(String type){
        List<GeneralModule> returnList = new ArrayList<>();
        for (GeneralModule module: this.modules){   // fill modules HashMap
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
