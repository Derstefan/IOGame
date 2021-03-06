package org.iogame.model.planet.buildings.storages;

import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;

import java.util.HashMap;

public class SevitStorage extends Building {
    public SevitStorage() {
        super(EBuilding.SEVIT_MINE);
    }

    @Override
    public void activate(Planet planet) {
        int lvl = getLvl();
        HashMap<EResource,Double> additionalAmount = EResource.emptyContainer();
        additionalAmount.put(EResource.SEVIT,100.0*lvl);
        planet.getStorage().addAmount(additionalAmount);
    }

    @Override
    public void update(long delta) {


    }
}
