package org.iogame.model.planet;


import org.iogame.model.enums.EResource;

import java.util.HashMap;
import java.util.Map;

public class ResourceDeposit {
    private final Map<EResource, Double> deposit;
    private final Map<EResource, Double> accessability;

    public ResourceDeposit() {
        this.deposit = new HashMap<>();
        this.accessability = new HashMap<>();
        // TODO: generate random occurence for each ressource
    }

    public boolean mineResource(EResource type, double amount) {
        if (deposit.containsKey(type)) {
            if (amount <= deposit.get(type)){
                deposit.put(type, deposit.get(type) - amount);
                return true;
            }
        }
        return false;
    }

    public Map<EResource, Double> getDeposit() {
        return deposit;
    }

    public double getAccessability(EResource r) {
        if (!accessability.containsKey(r)) {
            return 0.0D;
        }
        return accessability.get(r);
    }
}
