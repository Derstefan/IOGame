package org.iogame.model.planet.buildings.mines;

import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

import java.util.HashMap;

public class SevitMine extends Building {
    public SevitMine() {
        super(EBuilding.SEVIT_MINE);
    }

    @Override
    public void activate(Planet planet) {
        int lvl = getLvl();
        HashMap<EResource,Double> additionalAmount = EResource.emptyContainer();
        additionalAmount.put(EResource.SEVIT,3.0*lvl);
        additionalAmount.put(EResource.NEDRIL,0.6*lvl);
        planet.getMiningManager().addMiningAmount(additionalAmount);
    }

    @Override
    public void update(long delta) {


    }
}
