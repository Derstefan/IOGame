package org.iogame.model.planet;

import org.iogame.model.data.EBuilding;
import org.iogame.model.planet.buildings.Building;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
The BuildingManager organizes all the Buildings and the BuildingQueue on the planet
 */
public class BuildingManager {
    private final Map<EBuilding, Building> buildings;
    private final List<EBuilding> buildingQueue;
    private final Storage storage;
    private final Planet planet;


    private double remainingTime;

    public BuildingManager(Storage storage, Planet planet) {
        this.buildings = new HashMap<>();
        this.buildingQueue = new LinkedList<>();
        this.storage = storage;
        this.planet = planet;
    }

    public void loop(double delta){
        if(!buildingQueue.isEmpty()){
            remainingTime-=delta;
            if(remainingTime<=0){
                build(buildingQueue.get(0));
                if(!buildingQueue.isEmpty()){
                    remainingTime=Building.getDuration(buildingQueue.get(0));
                } else {
                    remainingTime =0;
                }
            }
        }
    }

    /**
     * Put new building on the buildingQueue
     */
    public boolean startBuild(EBuilding type){
        if(storage.removeResources(Building.getCosts(type))) {
            if(buildingQueue.isEmpty()){
                remainingTime = Building.getDuration(type);
            }
            buildingQueue.add(type);
            return true;
        } else {
            //TODO: message <<not enouth resources
            return false;
        }
    }

    private void build(EBuilding type){
        Building b = Building.getInstance(type);
        if (!buildings.containsKey(type)){
            buildings.put(type,b);
        } else {
            b= buildings.get(type);
            b.lvlUp();
        }
        b.updateBuilding(planet);

        buildingQueue.remove(0);
    }

    public boolean cancel(int index){
        if(index<buildingQueue.size()){
            EBuilding b = buildingQueue.get(index);
            storage.addResources(Building.getCosts(b));
            return true;
        }
        return false;
    }

    public boolean destroy(EBuilding type){
        if (buildings.containsKey(type)){
            Building b = buildings.get(type);
            b.lvlDown();
            b.destroyBuilding(planet);
            if(b.getLvl()==0){
                buildings.put(type,null);
            }
            return true;
        }
        return false;
    }
}
