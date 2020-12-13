package org.iogame.model.fleet.ship;

import org.iogame.model.data.EResource;
import org.iogame.model.data.dataModulesDelete;
import org.iogame.model.fleet.ship.modules.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// idea is that first one needs to instantiate a blueprint of a subclass of ShipBlueprint and then pass it on to
// a ShipBuilder, which then creates a ship out of it
// is not made abstract,
public class Blueprint {
    // general parameters
    private final String id;
    private HashMap<EResource, Double> cost;
    private double buildingTime = 0.0;
    // body properties
    private int size = 0; // number of Modules of instance BodyMod
    private int slot = 0; // available slots
    private double speed = 0.0;
    private double agility = 0.0;
    // special properties
    private List<String> bonus = new ArrayList<>();
    private double cargoVolume = 0;
    // defense properties
    private double hitpoints = 0.0;
    private double armor = 0.0;
    private double shield = 0.0;
    // private double shieldRegeneration = 0.0; // FIXME: is module specific; total value not helpful
    // attack properties;
    private double damage = 0.0;
    private ArrayList<String> damageType = new ArrayList<>();
    // List of modules
    private List<GeneralModule> modules;

    // TODO: Maybe use this constructor in the blueprint editor, to create an empty blueprint and add modules one after the other
    public Blueprint(String id){
        this.id = id;
    }

    // Super Constructor which will be used by predesigned specific Blueprint Subclasses
    // TODO: Check for number of BodyMod Modules; just 1 type allowed!
    public Blueprint(String id, List<GeneralModule> modules){
        this.id = id;
        this.modules = modules;

        this.cost = new HashMap<>();  // running Blueprint.cost
        for(EResource res: EResource.values()){ // initializes all EResources cost to 0.0
            this.cost.put(res, 0.0);
        }

        // Loop through all modules of the Blueprint
        for (GeneralModule module: this.modules){
            // calculate overall variable values
            // 1) General Variables which scale linearly
            // calculate Blueprint costs
            for(EResource res: module.getTotalCost().keySet()){
                Double runningCost = this.cost.get(res) + module.getTotalCost().get(res);
                this.cost.put(res, runningCost);
            }
            this.buildingTime += module.getTotalDuration();

            // Body Module (JUST ONE)
            if(module instanceof BodyMod) {
                // 2.0) Body Variables which stay identical
                this.speed = ((BodyMod) module).getSpeed();
                // 2.1) Body Variables which scale linearly
                this.slot = module.getTotalSlot();
                this.size = module.getQuantity();
                // 2.2) Body Variables which scale according to function in data/dataModules
                this.agility = dataModulesDelete.calculateAgility(((BodyMod) module).getAgility(), this.size);

            // Special Modules
            } else if(module instanceof SpecialMod){
                this.bonus.add(((SpecialMod) module).getBonus());
                this.cargoVolume += ((SpecialMod) module).getCargoVolume();

            // Defense Modules
            } else if(module instanceof DefenseMod){
                this.hitpoints += ((DefenseMod) module).getTotalHitPoints();
                this.armor += ((DefenseMod) module).getTotalArmor();
                this.shield += ((DefenseMod) module).getTotalShield();
                
            // Attack Modules
            } else if(module instanceof AttackMod){
                this.damage += ((AttackMod) module).getTotalDamage();
                this.damageType.add(((AttackMod) module).getDamageType());
                ((AttackMod) module).setAccuracy(dataModulesDelete.calculateAccuracy(
                        ((AttackMod) module).getBasicAccuracy(), this.agility));

            } else {
                System.out.println("Module " +  module.toString() + " is not assigned to any type of " +
                        "{\"body\", \"special\", \"defense\", \"attack\"}.");
            }
        }
        //


    }
    // Getter
    public List<GeneralModule> getModulesOfType(String type){
        List<GeneralModule> returnList = new ArrayList<>();
        for (GeneralModule module: this.modules){   // fill modules HashMap
            if(type.equals("body")  && module instanceof BodyMod) {
                returnList.add(module);
            } else if(type.equals("special") && module instanceof SpecialMod){
                returnList.add(module);
            } else if(type.equals("defense") && module instanceof DefenseMod){
                returnList.add(module);
            } else if(type.equals("attack") && module instanceof AttackMod){
                returnList.add(module);
            } else {
                System.out.println("Module " +  module.toString() + " is not assigned to any type of " +
                        "{\"body\", \"special\", \"defense\", \"attack\"}.");
            }
        }
        return returnList;
    }

    // Getter
    public String getId() {
        return id;
    }

    public HashMap<EResource, Double> getCost() {
        return cost;
    }

    public double getBuildingTime() {
        return buildingTime;
    }

    public int getSize() {
        return size;
    }

    public int getSlot() {
        return slot;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAgility() {
        return agility;
    }

    public List<String> getBonus() {
        return bonus;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public double getHitpoints() {
        return hitpoints;
    }

    public double getArmor() {
        return armor;
    }

    public double getShield() {
        return shield;
    }

    public double getDamage() {
        return damage;
    }

    public ArrayList<String> getDamageType() {
        return damageType;
    }

    public List<GeneralModule> getModules() {
        return modules;
    }

    // Setter

    public void setCost(HashMap<EResource, Double> cost) {
        this.cost = cost;
    }

    public void setBuildingTime(double buildingTime) {
        this.buildingTime = buildingTime;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setBonus(List<String> bonus) {
        this.bonus = bonus;
    }

    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public void setHitpoints(double hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDamageType(ArrayList<String> damageType) {
        this.damageType = damageType;
    }

}
