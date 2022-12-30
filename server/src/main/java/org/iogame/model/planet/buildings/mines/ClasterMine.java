package org.iogame.model.planet.buildings.mines;

import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

public class ClasterMine extends Building {
    public ClasterMine() {
        super(EBuilding.CLASTER_MINE);
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
