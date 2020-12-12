package org.iogame.model.planet.buildings;

import org.iogame.model.data.EBuilding;
import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;

public class LithiumMine extends Building{
    public LithiumMine() {
        super(EBuilding.LITHIUMMINE);
    }

    @Override
    public void updateBuilding(Planet planet) {
        double rate = planet.getMiningManager().getMiningRate().get(EResource.LITHIUM);
        planet.getMiningManager().getMiningRate().put(EResource.LITHIUM,rate + 10*this.getLvl());
    }

    @Override
    public void destroyBuilding(Planet planet) {
        double rate = planet.getMiningManager().getMiningRate().get(EResource.LITHIUM);
        planet.getMiningManager().getMiningRate().put(EResource.LITHIUM,rate - 10*(this.getLvl()+1));
    }
}
