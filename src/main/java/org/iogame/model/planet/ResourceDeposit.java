package org.iogame.model.planet;


import org.iogame.model.enums.EResource;

import java.util.HashMap;

public class ResourceDeposit {
    private HashMap<EResource, Double> deposit = new HashMap<EResource, Double>();
    private HashMap<EResource, Double> accessability = new HashMap<EResource, Double>();

    public ResourceDeposit() {
        // TODO: generate random occurence for each ressource
    }

    public boolean mineResource(EResource type, double amount){
        if(amount <= deposit.get(type)){
            deposit.put(type, deposit.get(type) - amount);
            return true;
        }
        return false;
    }

    public HashMap<EResource, Double> getDeposit() {
        return deposit;
    }

    public double getAccessability(EResource r) {
        return accessability.get(r);
    }
}
