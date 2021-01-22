package org.iogame.model.planet.buildings.space;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class SpaceStation extends Building {
    public SpaceStation() {
        super(EBuilding.SPACESTATION);
    }

    @Override
    public void activate(Planet planet) {

    }
}
