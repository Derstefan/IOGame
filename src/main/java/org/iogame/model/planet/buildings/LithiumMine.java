package org.iogame.model.planet.buildings;

import org.iogame.model.data.EBuilding;
import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;

public class LithiumMine extends Building{
    public LithiumMine() {
        super(EBuilding.LITHIUMMINE);
    }

    @Override
    public void updateValues(Planet planet) {
        //getLvl();
//        planet.getMiningManager().set
    }

    @Override
    public void update(long delta) {

    }
}
