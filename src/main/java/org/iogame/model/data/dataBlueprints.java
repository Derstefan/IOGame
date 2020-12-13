package org.iogame.model.data;

import org.iogame.model.fleet.ship.Blueprint;
import org.iogame.model.fleet.ship.blueprints.Genesis;
import org.iogame.model.fleet.ship.modules.GeneralModule;

import java.util.HashMap;
import java.util.List;

/*Holds the hard coded information of predesignated blueprints together with the enum EBlueprint
* If one wants to add a new Blueprint:
* 1.) EBlueprint: add enum Instance in EBlueprint with id and description
* 2.) add specific subclass of Blueprint; class name should be the enum.getId() of the respective enum in EBlueprint
* 3.) dataBlueprints: add else if cause containing module configuration info in method setBlueprintConfiguration
* 4.) dataBlueprints: add else if cause containing reference to specific constructor in method */

public abstract class dataBlueprints {
    private static HashMap<EBlueprint, HashMap<EModule, Integer>> allBlueprintsConfiguration = new HashMap<>();
    private static boolean allBlueprintsInstantiated = false;


    // not directly accessible from outside; indirectly accessed by instantiateAllBlueprints()
    // assures that always all blueprints hardcoded here are instantiated without redundant calls
    // TODO: How to add new Blueprints or upgrade while running?
    private static Blueprint instantiateBlueprint(EBlueprint eBlueprint){
        HashMap<EModule, Integer> blueprintConfiguration = dataBlueprints.getBlueprintConfiguration(eBlueprint);
        if(eBlueprint == EBlueprint.GENESIS){
            List<GeneralModule> modules = dataModules.instantiateModules(blueprintConfiguration);
            return new Genesis(eBlueprint.getId(), modules);
        } else{
            System.out.println("Blueprint configuration not found! Might not be included in " +
                    "/data/dataBlueprints methods and|or in /data/EBlueprint!");
            return null;
        }
    }

    // standard call when instantiating the ship manger
    // TODO: might build an own BlueprintManager, which will do that, probably in Game or Player
    public static HashMap<EBlueprint, Blueprint> instantiateAllBlueprints(){
        HashMap<EBlueprint, Blueprint> allBlueprints = new HashMap<>();
        for(EBlueprint eBlueprint: EBlueprint.values()){
            allBlueprints.put(eBlueprint, dataBlueprints.instantiateBlueprint(eBlueprint));
        }
        return allBlueprints;
    }

    // Getter
    public static HashMap<EModule, Integer> getBlueprintConfiguration(EBlueprint blueprint){
        dataBlueprints.setBlueprintConfiguration(blueprint);
        return dataBlueprints.allBlueprintsConfiguration.get(blueprint);
    }

    // Setter
    // fills the static HashMap always with desired values according to hardcoded information within this method
    private static void setBlueprintConfiguration(EBlueprint blueprint){
        if(blueprint == EBlueprint.GENESIS){
            HashMap<EModule, Integer> configuration = new HashMap<>();
            configuration.put(EModule.BODY1, 100);
            configuration.put(EModule.CARGO, 20);
            configuration.put(EModule.TURTLESHIELD, 20);
            configuration.put(EModule.RAPIDRAY, 20);
            dataBlueprints.allBlueprintsConfiguration.put(blueprint, configuration);
        }
    }



}
