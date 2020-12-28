package org.iogame.model.data;

import java.util.HashMap;

public enum EBuilding {
    LITHIUMMINE,
    UNIVERSITY;


    //statics
    public static HashMap<EResource, Double> getCosts(EBuilding type, int lvl) {
        HashMap<EResource, Double> costs = new HashMap<>();
        for (EResource r : EResource.values()) {
            costs.put(r, 0.1);
        }
        return costs;
    }

    public static double getDuration(EBuilding type, int lvl) {
        return 5.0 * 1000;
    }
}
