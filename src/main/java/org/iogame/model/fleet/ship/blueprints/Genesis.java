package org.iogame.model.fleet.ship.blueprints;

import org.iogame.model.fleet.ship.Blueprint;
import org.iogame.model.fleet.ship.modules.GeneralModule;

import java.util.List;

public class Genesis extends Blueprint {
    public Genesis(String id, List<GeneralModule> modules) {
        super(id, modules);
    }
}
