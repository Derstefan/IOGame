package model.planet;

import model.enums.EResource;

import java.util.HashMap;

public class Storage {
    private HashMap<EResource, Double> stock = new HashMap<EResource, Double>();

    public Storage() {
        for (EResource r : EResource.values()) {
            stock.put(r, 0.0);
        }
    }

    public double getResource(EResource type) {
        return stock.get(type);
    }

    public void addResource(EResource type, double amount) {
        stock.put(type, stock.get(type) + amount);
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
