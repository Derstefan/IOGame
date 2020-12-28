package org.iogame.model.planet;

import org.iogame.exceptions.NotBuildableException;
import org.iogame.model.data.EBuilding;
import org.iogame.model.data.EResource;
import org.iogame.model.planet.buildings.Building;

import java.util.*;

/*
The BuildingManager organizes all the Buildings and the BuildingQueue on the planet
 */
public class BuildingManager {
    private final int MAX_QUEUE_LENGTH = 5;

    private final Map<EBuilding, Building> buildings;
    private final List<EBuilding> buildingQueue;
    private final Set<EBuilding> avaiableBuildings;
    private final Storage storage;

    private final Planet planet;


    private double remainingDuration;

    public BuildingManager(Storage storage, Planet planet) {
        this.buildings = new HashMap<>();
        this.buildingQueue = new LinkedList<>();

        this.storage = storage;
        this.planet = planet;
        avaiableBuildings = new HashSet<>();
        avaiableBuildings.add(EBuilding.LITHIUMMINE);
        for (EBuilding type : EBuilding.values()) {
            buildings.put(type,Building.getInstance(type));
        }

    }


    public void update(long delta) {
        if (!buildingQueue.isEmpty()) {
            remainingDuration -= delta;
            if (remainingDuration <= 0) {
                build(buildingQueue.get(0));
                if (!buildingQueue.isEmpty()) {
                    EBuilding b = buildingQueue.get(0);
                    // += time for next element in queue
                    remainingDuration += EBuilding.getDuration(b, buildings.get(b).getLvl() + 1);
                } else {
                    remainingDuration = 0;
                }
            }
        }
    }

    /**
     * Put new building on the buildingQueue
     */
    public boolean startBuild(EBuilding type) throws NotBuildableException {
        if (isBuildable(type) && isAffordable(type) && isEnouthPlaceInQueue()) {
            storage.removeResources(EBuilding.getCosts(type, getLvlOfNextBuiding(type)));
            if (buildingQueue.isEmpty()) {
                remainingDuration = EBuilding.getDuration(type, buildings.get(type).getLvl() + 1);
            }
            buildingQueue.add(type);
            return true;
        }
        return false;
    }

    public boolean cancel(int index) {
        if (index < buildingQueue.size()) {
            EBuilding b = buildingQueue.get(index);
            buildingQueue.remove(index);
            storage.addResources(costsOf(b));
            return true;
        }
        return false;
    }

    public void swapQueueElements(int i1,int i2){
        if(i1<buildingQueue.size() && i2 < buildingQueue.size()){
            EBuilding temp = buildingQueue.get(i1);
            buildingQueue.set(i1,buildingQueue.get(i2));
            buildingQueue.set(i2,temp);
        }
    }


    private void build(EBuilding type) {
        Building b = buildings.get(type);
        b.lvlUp(planet);
        buildingQueue.remove(0);
    }

    public boolean destroy(EBuilding type) {
        Building b = buildings.get(type);
        if (b.getLvl() > 0) {
            b.lvlDown(planet);
            return true;
        }
        return false;
    }


    public double getRemainingDuration() {
        return remainingDuration;
    }

    public double getQueueDuration(){
        double duration= 0;
        Map<EBuilding,Integer> queueLvlCounter = new HashMap<>();
        for(EBuilding b:EBuilding.values()){
            queueLvlCounter.put(b,0);
        }
        duration+=remainingDuration;
        for(int i=1;i<buildingQueue.size();i++){
            EBuilding b = buildingQueue.get(i);
            queueLvlCounter.put(b, queueLvlCounter.get(b)+1);
            int lvl = buildings.get(b).getLvl() + queueLvlCounter.get(b);
            duration+=EBuilding.getDuration(b,lvl);
        }
        return duration;
    }

    public List<EBuilding> getBuildingQueue() {
        return buildingQueue;
    }

    public int getLvlOf(EBuilding type){
        return buildings.get(type).getLvl();
    }


    public Set<EBuilding> getAvaiableBuildings() {
        return avaiableBuildings;
    }

    public boolean isAffordable(EBuilding type) throws NotBuildableException {
        if (storage.hasEnouthResources(costsOf(type))) {
            return true;
        }
        throw new NotBuildableException();
    }

    public boolean isBuildable(EBuilding type) throws NotBuildableException {
        if (avaiableBuildings.contains(type)) {
            return true;
        }
        throw new NotBuildableException();
    }

    public boolean isEnouthPlaceInQueue() throws NotBuildableException {
        if (buildingQueue.size() < MAX_QUEUE_LENGTH) {
            return true;
        }
        throw new NotBuildableException();
    }

    public HashMap<EResource, Double> costsOf(EBuilding type) {
        return EBuilding.getCosts(type, getLvlOfNextBuiding(type));
    }

    private int getLvlOfNextBuiding(EBuilding type) {
        int lvl = buildings.get(type).getLvl();
        for (EBuilding b : buildingQueue) {
            if (b.equals(type)) {
                lvl++;
            }
        }
        return lvl+1;
    }

}
