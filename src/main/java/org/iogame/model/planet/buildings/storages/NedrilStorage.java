package org.iogame.model.planet.buildings.storages;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class NedrilStorage extends Building {
    public NedrilStorage() {
        super(EBuilding.NEDRIL_MINE);
    }

    @Override
    public void activate(Planet planet) {
        //getLvl();
//        planet.getMiningManager().set
    }

    @Override
    public void update(long delta) {

    }
}
