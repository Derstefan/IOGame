package iogame.model.planet;
import iogame.model.enums.EResource;

import java.util.HashMap;

public class Resource {
    private HashMap<EResource, Double> deposit = new HashMap<EResource, Double>();
    private HashMap<EResource, Double> miningRate = new HashMap<EResource, Double>();

    public Resource() {
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

    public HashMap<EResource, Double> getMiningRate() {
        return miningRate;
    }
}
