package org.iogame.model.planet.buildings.space;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class ShipYard extends Building {
    public ShipYard() {
        super(EBuilding.SHIPYARD);
    }

    @Override
    public void activate(Planet planet) {

    }
}
