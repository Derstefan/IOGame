package org.iogame.model.planet.buildings.defense;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class ShieldGenerator extends Building {
    public ShieldGenerator() {
        super(EBuilding.SHIELGENERATOR);
    }

    @Override
    public void activate(Planet planet) {

    }
}
