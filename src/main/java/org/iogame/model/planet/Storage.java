package org.iogame.model.planet;

import org.iogame.model.enums.EResource;

import java.util.HashMap;

public class Storage {
    private HashMap<EResource, Double> stock = new HashMap<>();
    private HashMap<EResource, Double> cap = new HashMap<>();

    public Storage() {
        for (EResource r : EResource.values()) {
            stock.put(r, 0.0);
        }
        for (EResource r : EResource.values()) {
            cap.put(r, 100.0);//TODO: Lookup vars
        }
    }

    public double getResource(EResource type) {
        return stock.get(type);
    }

    public HashMap<EResource, Double> getStock() {
        return stock;
    }

    public HashMap<EResource, Double> getCap() {
        return cap;
    }

    public void setCap(EResource type, double newCap) {
        this.cap.put(type,newCap);
    }

    public void addResource(EResource type, double amount) {
        if (amount >=cap.get(type) - stock.get(type)) {
            stock.put(type, cap.get(type));
        }
    }

    public void addResources(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            addResource(r, amount.get(r));
        }
    }

    public boolean removeResource(EResource type, double amount) {
        if (amount <= stock.get(type)) {
            stock.put(type, stock.get(type) - amount);
            return true;
        }
        return false;
    }

    public boolean removeResources(HashMap<EResource, Double> costs) {
        for (EResource r : EResource.values()) {
            if (removeResource(r, costs.get(r))) {
                return false;
            }
        }
        return true;
    }
}
