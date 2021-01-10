package org.iogame.model.planet;

import org.iogame.model.data.EResource;

import java.util.HashMap;

public class MiningManager {



    private final ResourceDeposit resourceDeposit;
    private final Storage storage;

    /*
    miningRate=startRate + miningAmount*buildingBoost*researchBoost
     */
    private final HashMap<EResource, Double> miningRate = new HashMap<EResource, Double>();

    private final HashMap<EResource, Double> startRate = new HashMap<>();
    private final HashMap<EResource, Double> miningAmount = new HashMap<>();
    private final HashMap<EResource, Double> buildingBoost = new HashMap<>();
    private final HashMap<EResource, Double> techBoost = new HashMap<>();

    // sustainability
    private HashMap<EResource, Double> efficiency = new HashMap<EResource, Double>();

    public MiningManager(ResourceDeposit resourceDeposit, Storage storage) {
        this.resourceDeposit = resourceDeposit;
        this.storage = storage;
        init();
    }

    public void update(long delta) {
        for (EResource r : EResource.values()) {
            double mineAmount = miningRate.get(r) * resourceDeposit.getAccessability(r);
            double resourceDeduction = mineAmount / efficiency.get(r);
            resourceDeposit.mineResource(r, resourceDeduction);
            storage.addResource(r, mineAmount);
        }
    }

    private void init(){

        resetStats();
    }

    public void resetStats(){
        for (EResource r : EResource.values()) {
            miningRate.put(r, 0.0);

            startRate.put(r,0.1);
            miningAmount.put(r,0.0);
            buildingBoost.put(r,1.0);
            techBoost.put(r,1.0);

            efficiency.put(r, 1.0);
        }
        computeMiningRate();
    }



    public void addMiningAmount(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            miningAmount.put(r,miningAmount.get(r)+amount.get(r));
        }
        computeMiningRate();
    }

    public void addBuildingBoost(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            buildingBoost.put(r,buildingBoost.get(r)+amount.get(r));
        }
        computeMiningRate();
    }

    public void addTechBoost(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            techBoost.put(r, techBoost.get(r)+amount.get(r));
        }
        computeMiningRate();
    }
    private void computeMiningRate(){
        for (EResource r : EResource.values()) {
            miningRate.put(r, startRate.get(r)+ miningAmount.get(r)*buildingBoost.get(r)* techBoost.get(r));
        }
    }

    public HashMap<EResource, Double> getMiningRate() {
        return miningRate;
    }

    public HashMap<EResource, Double> getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(HashMap<EResource, Double> efficiency) {
        this.efficiency = efficiency;
    }
}
