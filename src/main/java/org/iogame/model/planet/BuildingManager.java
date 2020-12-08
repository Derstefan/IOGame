package org.iogame.model.planet;

import org.iogame.model.enums.EBuilding;
import org.iogame.model.planet.buildings.Building;

import java.util.HashMap;
import java.util.List;

/*
The BuildingManager organizes all the Buildings and the BuildingQueue on the planet
 */
public class BuildingManager {
    private HashMap<EBuilding, Building> buildings;
    private Storage storage;
    private Planet planet;

    private List<EBuilding> buildingQueue;
    private double remainingTime;

    public BuildingManager(Storage storage,Planet planet) {
        this.storage = storage;
        this.planet = planet;
        for (EBuilding e: EBuilding.values()) {
            buildings.put(e,null);
        }
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
        if(buildings.get(type)==null){
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
        if(buildings.get(type)!=null){
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
