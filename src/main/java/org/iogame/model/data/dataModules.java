package org.iogame.model.data;

import org.iogame.model.fleet.ship.modules.GeneralModule;
import org.iogame.model.fleet.ship.modules.attack.RapidRay;
import org.iogame.model.fleet.ship.modules.body.Body1;
import org.iogame.model.fleet.ship.modules.defense.TurtleShield;
import org.iogame.model.fleet.ship.modules.special.Cargo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class dataModules {
    // general parameters of Module Class
    private static HashMap<EResource, Double> cost = new HashMap<>();
    private static double buildingTime;

    // body parameters of BodyMod Class
    private static int slot;
    private static double speed;
    private static double agility;

    // special parameters of SpecialMod Class
    private static String bonus;
    private static int max;
    private static double cargoVolume;

    // defense parameters of DefenseMod Class
    private static String defenseType;
    private static double hitPoints;
    private static double regeneration;

    // attack parameters of AttackMod Class
    private static String damageType;
    private static int recharge;
    private static double damage;
    private static double basicAccuracy;


    // TODO: What is the best value to reset it to? null seems to cause some problems
    // resets all Parameters before new assignment by
    private static void resetParameters(){
        // general parameters of Module Class
        cost = new HashMap<>();
        buildingTime = Double.parseDouble(null);

        // body parameters of BodyMod Class
        slot = Integer.parseInt(null);
        speed = Double.parseDouble(null);
        agility = Double.parseDouble(null);

        // special parameters of SpecialMod Class
        bonus = null;
        max = Integer.parseInt(null); // or better Integer.MAX_VALUE ?!
        cargoVolume = Double.parseDouble(null);

        // defense parameters of DefenseMod Class
        defenseType = null;
        hitPoints = Double.parseDouble(null);
        regeneration = Double.parseDouble(null);

        // attack parameters of AttackMod Class
        damageType = null;
        recharge = Integer.parseInt(null);
        damage = Double.parseDouble(null);
        basicAccuracy = Double.parseDouble(null);
    }

    // first resets parameters and then fills in the hard coded information of respective EModule
    private static void setModuleParameters(EModule eModule){
        resetParameters();

        switch(eModule){
            // all body modules
            case BODY1:
                cost.put(EResource.CLASTER, 1.0);
                cost.put(EResource.SEVIT, 2.0);
                cost.put(EResource.NEDRIL, 3.0);
                cost.put(EResource.JIVANA, 4.0);
                buildingTime = 10.0;
                slot = 2;
                speed = 1.0;
                agility = 1.0;


            // all special modules
            case CARGO:
                cost.put(EResource.CLASTER, 1.0);
                cost.put(EResource.SEVIT, 2.0);
                cost.put(EResource.NEDRIL, 3.0);
                cost.put(EResource.JIVANA, 4.0);
                slot = 1;
                buildingTime = 10.0;
                cargoVolume = 1000.0;


            // all defense modules
            case TURTLESHIELD:
                cost.put(EResource.CLASTER, 1.0);
                cost.put(EResource.SEVIT, 2.0);
                cost.put(EResource.NEDRIL, 3.0);
                cost.put(EResource.JIVANA, 4.0);
                slot = 1;
                buildingTime = 10.0;
                defenseType = "shield";
                hitPoints = 1;
                regeneration = 0.5;



            // all weapon modules
            case RAPIDRAY:
                cost.put(EResource.CLASTER, 1.0);
                cost.put(EResource.SEVIT, 2.0);
                cost.put(EResource.NEDRIL, 3.0);
                cost.put(EResource.JIVANA, 4.0);
                slot = 1;
                buildingTime = 10.0;
                damageType = "armor";
                recharge = 2;
                damage = 1.0;
                basicAccuracy = 1.0;



            default:
                System.out.println("Module Parameters not found! Might not be included in " +
                        "dataModules.getModuleParameters(...) function! and|or in /data/EModules!");
        }
    }

    // first sets parameters of EModule to then instantiate the respective GeneralModule
    public static GeneralModule instantiateModule(EModule eModule, int quantity){
        setModuleParameters(eModule);
        switch(eModule){
            // all body modules
            case BODY1:
                return new Body1(eModule, eModule.getName(), eModule.getDescription(),
                        cost, buildingTime, quantity, slot, speed,agility);

            // all special modules
            case CARGO:
                return new Cargo(eModule, eModule.getName(), eModule.getDescription(),
                        cost, slot,buildingTime, quantity,
                        bonus, max, cargoVolume);

            // all defense modules
            case TURTLESHIELD:
                return new TurtleShield(eModule, eModule.getName(), eModule.getDescription(),
                        cost, slot,buildingTime, quantity,
                        defenseType, hitPoints, regeneration);


            // all weapon modules
            case RAPIDRAY:
                return new RapidRay(eModule, eModule.getName(), eModule.getDescription(),
                        cost, slot,buildingTime, quantity,
                        damageType, recharge, damage, basicAccuracy);


            default:
                System.out.println("Module Parameters not found! Might not be included in " +
                        "dataModules.getModuleParameters(...) function! and|or in /data/EModules!");
                return null;
        }
    }

    // is called by dataBlueprints to instantiate Blueprints with their specific configuration
    public static List<GeneralModule> instantiateModules(HashMap<EModule, Integer> configuration){
        List<GeneralModule> modules = new ArrayList<>();
        for(EModule eModule: configuration.keySet()){
            modules.add(instantiateModule(eModule, configuration.get(eModule)));
        }
        return modules;
    }


    /*some functions to calculation GeneralModule parameter, which are dependant on others*/
    // calculates agility of a blueprint
    public static double calculateAgility(double basicAgility, int size){
        return basicAgility;
    }

    // will account for damageTypes and their influence on differnt damage on different defense types
    public static double calculateEffectiveDamage(double damage, String damageType){
        return damage;
    }

    // TODO: Implement calculateHitAccuracy which also takes both aggressor and defensor into account;
    //  Maybe this would be put somewhere related to the Battle class
    // calculates specific accuracy of a weapon
    public static double calculateAccuracy(double basicAccuracy, double agility){
        return basicAccuracy;
    }


}
