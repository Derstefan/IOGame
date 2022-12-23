package org.iogame.model.planet.buildings.general;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class University extends Building {
    public University() {
        super(EBuilding.UNIVERSITY);
    }

    @Override
    public void activate(Planet planet) {

    }
}
