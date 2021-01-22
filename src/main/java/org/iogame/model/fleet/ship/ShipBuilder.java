package org.iogame.model.fleet.ship;

import org.iogame.model.data.EBlueprint;
import org.iogame.model.data.EBuilding;
import org.iogame.model.planet.Planet;
import org.iogame.model.planet.buildings.Building;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ShipBuilder {
    // FIXME: not necessarily important at every planet, maybe better at Player or Game
    private HashMap<EBlueprint, Blueprint> blueprints; // not necessarily
    private HashMap<EBlueprint, Boolean> isEnabled;
    private final Planet planet;
    private List<EBlueprint> buildingQueue;
    private double remainingTime;

    public ShipBuilder(HashMap<EBlueprint, Blueprint> blueprints, Planet planet){
        this.blueprints = blueprints;
        // Initialize all Blueprints to false as default; have to be enabled
        for (EBlueprint eBlueprint: blueprints.keySet()) {
            isEnabled.put(eBlueprint, false);
        }
        this.planet = planet;
        this.buildingQueue = new LinkedList<>();
    }

    public void update(long delta){
        if(!buildingQueue.isEmpty()){
            remainingTime-=delta;
            if(remainingTime<=0){
                build(buildingQueue.get(0));
                if(!buildingQueue.isEmpty()){
                    remainingTime= Building.getDuration(buildingQueue.get(0));
                } else {
                    remainingTime =0;
                }
            }
        }
    }

    /**
     * Put new building on the buildingQueue
     */
    public boolean startBuild(EBlueprint eBlueprint){
        if(isEnabled.get(eBlueprint)){
            if(planet.getStorage().removeResources(blueprints.get(eBlueprint).getCost())) {
                if(buildingQueue.isEmpty()){
                    remainingTime = blueprints.get(eBlueprint).getBuildingTime();
                }
                buildingQueue.add(eBlueprint);
                return true;
            } else {
                //TODO: Warning: "Not enough ressources to build this specific blueprint!"
                return false;
            }
        } else {
            //TODO: Warning: "Building of specific blueprint is not enabled yet!"
            return false;
        }

    }

    private void build(EBlueprint eBlueprint){
        Ship ship = Building.getInstance(eBlueprint);
        if (!buildings.containsKey(eBlueprint)){
            buildings.put(eBlueprint,b);
        } else {
            b= buildings.get(eBlueprint);
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
