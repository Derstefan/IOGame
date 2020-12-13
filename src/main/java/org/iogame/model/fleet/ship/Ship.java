package org.iogame.model.fleet.ship;

import org.iogame.model.data.EResource;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Ship {
    // general parameters
    private final String name;
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


}
