package src.main.java.org.iogame.model.data;

import java.util.HashMap;
import org.iogame.model.data.EResource;
import src.main.java.org.iogame.model.data.EModule;
import src.main.java.org.iogame.model.fleet.ship.modules.attack.RapidRay;
import src.main.java.org.iogame.model.fleet.ship.modules.body.Body1;
import src.main.java.org.iogame.model.fleet.ship.modules.Module;
import src.main.java.org.iogame.model.fleet.ship.modules.defense.TurtleShield;
import src.main.java.org.iogame.model.fleet.ship.modules.special.Cargo;

public abstract class dataModules {

    public static Module instantiateModule(EModule emodule, int quantity){
        // general parameters of Module Class
        HashMap<EResource, Double> cost = new HashMap<>();
        double duration = 0;

        // body parameters of BodyMod Class
        int slot = 1;
        double agility;

        // special parameters of SpecialMod Class
        String bonus = null;
        int max = Integer.MAX_VALUE;
        double cargoVolume;

        // defense parameters of DefenseMod Class
        String defenseType;
        double hitPoints;
        double regeneration = 0;

        // attack parameters of AttackMod Class
        String attackType;
        int recharge;
        double attackRate;

        switch(emodule){
            // all body modules
            case BODY1:
                cost.put(EResource.RES1, 1.0);
                cost.put(EResource.RES2, 2.0);
                cost.put(EResource.RES3, 3.0);
                cost.put(EResource.LITHIUM, 4.0);
                duration = 10.0;
                slot = 2;
                agility = 1.0;
                return new Body1(emodule, emodule.getName(), emodule.getDescription(),
                        cost, duration, quantity, slot, agility);

            // all special modules
            case CARGO:
                cost.put(EResource.RES1, 1.0);
                cost.put(EResource.RES2, 2.0);
                cost.put(EResource.RES3, 3.0);
                cost.put(EResource.LITHIUM, 4.0);
                slot = 1;
                duration = 10.0;
                cargoVolume = 1000.0;
                return new Cargo(emodule, emodule.getName(), emodule.getDescription(),
                        cost, slot,duration, quantity,
                        bonus, max, cargoVolume);

            // all defense modules
            case TURTLESHIELD:
                cost.put(EResource.RES1, 1.0);
                cost.put(EResource.RES2, 2.0);
                cost.put(EResource.RES3, 3.0);
                cost.put(EResource.LITHIUM, 4.0);
                slot = 1;
                duration = 10.0;
                defenseType = "shield";
                hitPoints = 1;
                regeneration = 0.5;
                return new TurtleShield(emodule, emodule.getName(), emodule.getDescription(),
                        cost, slot,duration, quantity,
                        defenseType, hitPoints, regeneration);


            // all weapon modules
            case RAPIDRAY:
                cost.put(EResource.RES1, 1.0);
                cost.put(EResource.RES2, 2.0);
                cost.put(EResource.RES3, 3.0);
                cost.put(EResource.LITHIUM, 4.0);
                slot = 1;
                duration = 10.0;
                attackType = "armor";
                recharge = 2;
                attackRate = 1.0;
                return new RapidRay(emodule, emodule.getName(), emodule.getDescription(),
                        cost, slot,duration, quantity,
                        attackType, recharge, attackRate);


            default:
                System.out.println("Module Parameters not found! Might not be included in " +
                        "dataModules.getModuleParameters(...) function! and|or in /data/EModules!");
                return null;
        }
    }
}
