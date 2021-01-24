package org.iogame.model.research;

import org.iogame.model.data.EResource;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;

import java.util.*;

public enum ETech {
    ARCHITECTURE,
    MINERALOGY,
    AI,
    WAREHOUSE_LOGISTICS,
    OUT_OF_SPACE,
    BEYOND_SPACE,
    ROBOTS,
    DEFENSE;


    private final Set<ETech> techReqs = new HashSet<>();


    private ETech() {
        init();
    }

    private void init() {

        // TECH REQUIREMENTS
        // TODO: how to add this requirements? so it's adding "null".
        switch (this.name()) {
            case "MINERALOGY":
         //       techReqs.add(ETech.AI);
            case "ARCHITECTURE":

            case "AI":

            case "WAREHOUSE_LOGISTICS":
          //      techReqs.add(ETech.AI);

            case "OUT_OF_SPACE":
                //        techReqs.add(ETech.ROBOTS);
            case "BEYOND_SPACE":
                //techReqs.add(ETech.OUT_OF_SPACE);
            case "DEFENSE":

            case "ROBOTS":
           //     techReqs.add(ETech.AI);

            default:
        }
    }

    public void activate(Player player) {
        for(Planet p: player.getControlledPlanets()) {

            p.resetTechStats();

            switch (this.name()) {
                case "MINERALOGY":
                    HashMap<EResource, Double> amount = EResource.emptyContainer();
                    for (EResource type : EResource.values()) {
                        amount.put(type, 0.1);
                    }
                    p.getMiningManager().addTechBoost(amount);
                case "ARCHITECTURE":

                case "AI":

                case "WAREHOUSE_LOGISTICS":

                case "OUT_OF_SPACE":

                case "BEYOND_SPACE":

                case "DEFENSE":

                case "ROBOTS":

                default:
            }
        }
    }

    public Set<ETech> getTechReqs() {
        return techReqs;
    }
}

