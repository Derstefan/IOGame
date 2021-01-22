package org.iogame.model.fleet.ship;

import org.iogame.model.data.EBlueprint;
import org.iogame.model.data.dataBlueprints;

import java.util.HashMap;

public class BlueprintManager {
    private final HashMap<EBlueprint, Blueprint> blueprints;

    public BlueprintManager() {
        this.blueprints = dataBlueprints.instantiateAllBlueprints();
    }

    public void update(){
        for(Blueprint blueprint: blueprints.values()){
            blueprint.update();
        }
    }

    // Getter
    public HashMap<EBlueprint, Blueprint> getBlueprints() {
        return blueprints;
    }
}
