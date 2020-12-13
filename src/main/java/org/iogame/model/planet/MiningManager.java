package org.iogame.model.planet;

import org.iogame.model.data.EResource;

import java.util.HashMap;

public class MiningManager {

//    private final HashMap<EResource, Double> startRate = LookUp.getStartMiningRate();

    private ResourceDeposit resourceDeposit;
    private Storage storage;
    private HashMap<EResource, Double> miningRate = new HashMap<EResource, Double>();

    // sustainability
    private HashMap<EResource, Double> efficiency = new HashMap<EResource, Double>();

    public MiningManager(ResourceDeposit resourceDeposit, Storage storage) {
        this.resourceDeposit = resourceDeposit;
        this.storage = storage;
        // sustainability research
        for (EResource r : EResource.values()) {
            miningRate.put(r, 0.0);//startRate.get(r)
            efficiency.put(r, 1.0);
        }
    }

    public void update(long delta) {

        for (EResource r : EResource.values()) {
            double mineAmount = miningRate.get(r) * resourceDeposit.getAccessability(r);
            double resourceDeduction = mineAmount / efficiency.get(r);
            resourceDeposit.mineResource(r, resourceDeduction);
            storage.addResource(r, mineAmount);
        }

    }

    public HashMap<EResource, Double> getMiningRate() {
        return miningRate;
    }

    public void setMiningRate(HashMap<EResource, Double> miningRate) {
        this.miningRate = miningRate;
    }

    public HashMap<EResource, Double> getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(HashMap<EResource, Double> efficiency) {
        this.efficiency = efficiency;
    }
}
