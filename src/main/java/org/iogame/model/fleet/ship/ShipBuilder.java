package org.iogame.model.fleet.ship;

import org.iogame.model.data.EBlueprint;
import org.iogame.model.data.dataBlueprints;

import java.util.HashMap;

public class ShipBuilder {
    // FIXME: not necessarily important at every planet, maybe better at Player or Game
    private HashMap<EBlueprint, Blueprint> blueprints; // not necessarily

    public ShipBuilder(){
        this.blueprints = dataBlueprints.instantiateAllBlueprints();
    }
}
