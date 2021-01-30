package org.iogame.model.planet;

import org.iogame.model.data.EResource;

import java.util.HashMap;

public class Storage {
    private HashMap<EResource, Double> stock = new HashMap<>();

    /**
     * cap=startCap + buildingAmount*bouldingBoost*techBoost
     */
    private HashMap<EResource, Double> cap = new HashMap<>();

    private HashMap<EResource,Double> startCap = EResource.emptyContainer();
    private HashMap<EResource,Double> amount = EResource.emptyContainer();
    private HashMap<EResource,Double> buildingBoost = EResource.emptyContainer();
    private HashMap<EResource,Double> techBoost = EResource.emptyContainer();

    public Storage() {

        init();
    }
    private void init(){
        for (EResource r : EResource.values()) {
            stock.put(r, 80.0);
        }
        resetBuildingStats();
        resetTechStats();

    }

    public void resetBuildingStats(){
        for (EResource r : EResource.values()) {
            cap.put(r, 0.0);//TODO: Lookup vars

            startCap.put(r,100.0);
            amount.put(r,0.0);
            buildingBoost.put(r,1.0);
        }
        computeCap();
    }

    public void resetTechStats(){
        for (EResource r : EResource.values()) {
            techBoost.put(r,1.0);
        }
        computeCap();
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
        if(hasEnouthResources(costs)){
            for (EResource r : EResource.values()) {
                removeResource(r, costs.get(r));
            }
            return true;
        }
        return false;
    }

    public boolean hasEnouthResources(HashMap<EResource, Double> costs){
        for (EResource r : EResource.values()) {
            if(costs.get(r) > stock.get(r)){
                return false;
            }
        }
        return true;
    }


    public void addAmount(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            amount.put(r,amount.get(r)+amount.get(r));
        }
        computeCap();
    }

    public void addBuildingBoost(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            buildingBoost.put(r,buildingBoost.get(r)+amount.get(r));
        }
        computeCap();
    }

    public void addResearchBoost(HashMap<EResource, Double> amount) {
        for (EResource r : EResource.values()) {
            techBoost.put(r, techBoost.get(r)+amount.get(r));
        }
        computeCap();
    }
    private void computeCap(){
        for (EResource r : EResource.values()) {
            cap.put(r, startCap.get(r)+ amount.get(r)*buildingBoost.get(r)* techBoost.get(r));
        }
    }

    public String stockToString(){
        String s = "Res:";
        for (EResource r : EResource.values()) {
            s+= r + " : " + stock.get(r) + " ";
        }
        return s+"\n";
    }

}
