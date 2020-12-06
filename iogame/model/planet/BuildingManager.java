package iogame.model.planet;

import iogame.model.planet.buildings.Building;

import java.util.List;

/*
The BuildingManager organizes all the Buildings and the BuildingQueue on the planet
 */
public class BuildingManager {
   private List<Building> buildings;
    private Storage storage;

   private List<Building> buildingQueue;
   private double remainingTime;


    public BuildingManager(Storage storage) {
        this.storage = storage;
    }

    public void loop(double delta){
        if(!buildingQueue.isEmpty()){
            remainingTime-=delta;
            if(remainingTime<=0){
                Building b = buildingQueue.get(0);
                if(!isBuilt(b)){
                    buildings.add(b);
                }

                // lvl up for lvl-up-buildings inside updatebuilding method
                b.updateBuilding();

                buildingQueue.remove(0);
                if(!buildingQueue.isEmpty()){
                    remainingTime=buildingQueue.get(0).getDuration();
                } else {
                    remainingTime =0;
                }
            }
        }
    }
/*
Check if the Building was already Build (same type => lvl up)
 */
    private boolean isBuilt(Building newBuilding){
        for (Building b: buildings) {
            if(b.getClass().equals(newBuilding.getClass())){
                return true;
            }
        }
        return false;
    }

/*
Put new building on the buildingQueue
 */
    public void build(Building b){
        if(storage.removeResources(b.getCosts())) {
            if(buildingQueue.isEmpty()){
                remainingTime = b.getDuration();
            }
            buildingQueue.add(b);

        } else {
            //TODO: message <<not enouth resources
        }
    }
}
