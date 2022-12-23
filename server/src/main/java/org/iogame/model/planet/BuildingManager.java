package org.iogame.model.planet;

import org.iogame.exceptions.NotBuildableException;
import org.iogame.model.data.EResource;
import org.iogame.model.planet.buildings.Building;
import org.iogame.model.planet.buildings.EBuilding;
import org.iogame.model.research.ETech;

import java.util.*;

/*
The BuildingManager organizes all the Buildings and the BuildingQueue on the planet
 */
public class BuildingManager {
    private final int MAX_QUEUE_LENGTH = 5;

    private final Map<EBuilding, Building> buildings;
    private final List<EBuilding> buildingQueue;
    private final Set<EBuilding> availableBuildings;
    private final Storage storage;

    private final Planet planet;


    private double remainingDuration;

    /**
    speed = 1+buildingBoost*techBoost
     */
    private double speed = 1.0;
    private double buildingBoost = 0.0;
    private double techBoost = 0.0;


    public BuildingManager(Storage storage, Planet planet) {
        this.buildings = new HashMap<>();
        this.buildingQueue = new LinkedList<>();
        this.availableBuildings = new HashSet<>();
        this.storage = storage;
        this.planet = planet;
        init();
    }

    private void init(){
        availableBuildings.add(EBuilding.SEVIT_MINE);
        availableBuildings.add(EBuilding.CLASTER_MINE);
        availableBuildings.add(EBuilding.NEDRIL_MINE);
        for (EBuilding type : EBuilding.values()) {
            //only planetdepending avilable buildings (no omama -> no omamaMine) ?
            buildings.put(type, type.getInstance());
        }
        resetBuildingStats();
        resetTechStats();
    }

    public void resetBuildingStats() {
        buildingBoost = 0.0;
    }

    public void resetTechStats() {
        techBoost = 0.0;
    }



    public void update(long delta) {
        System.out.println("still: " + remainingDuration + " ms");
        if (!buildingQueue.isEmpty()) {
            remainingDuration -= delta;
            if (remainingDuration <= 0) {
                build(buildingQueue.get(0));
                if (!buildingQueue.isEmpty()) {
                    EBuilding b = buildingQueue.get(0);
                    // += time for next element in queue
                    remainingDuration += durationOf(b, buildings.get(b).getLvl() + 1);
                } else {
                    remainingDuration = 0;
                }
            }
        }
    }

    /**
     * Put new building on the buildingQueue.
     */
    public boolean startBuild(EBuilding type) throws NotBuildableException {
        // TODO: maxlvl ? -> in available Buildings?
        if (isBuildable(type)) {
            storage.removeResources(costsOfNext(type));
            if (buildingQueue.isEmpty()) {
                remainingDuration = durationOf(type, buildings.get(type).getLvl() + 1);
            }
            buildingQueue.add(type);
            return true;
        }
        return false;
    }

    /**
     * Stops building and remove it from queue.
     */
    public boolean cancel(int index) {
        if (index < buildingQueue.size()) {
            EBuilding b = buildingQueue.get(index);
            buildingQueue.remove(index);
            storage.addResources(costsOfNext(b));
            return true;
        }
        return false;
    }

    public void swapQueueElements(int i1, int i2) {
        if (0<i1 && i1 < buildingQueue.size() && 0<i2 && i2 < buildingQueue.size()) {
            EBuilding temp = buildingQueue.get(i1);
            buildingQueue.set(i1, buildingQueue.get(i2));
            buildingQueue.set(i2, temp);
        }
    }



    private void build(EBuilding type) {
        Building b = buildings.get(type);
        b.lvlUp();
        planet.updateBuildingStats();
        buildingQueue.remove(0);
    }



    public boolean destroy(EBuilding type) {
        Building b = buildings.get(type);
        if (b.getLvl() > 0) {
            b.lvlDown();
            planet.updateBuildingStats();
            return true;
        }
        return false;
    }


    /**
     * Checks all requirements of building this type
     * @param type
     * @return
     * @throws NotBuildableException
     */
    public boolean isBuildable(EBuilding type) throws NotBuildableException {
       // System.out.println("available:" + availableBuildings.contains(type) +"\nenouth res: " +storage.hasEnouthResources(costsOfNext(type)) + "\nqueue: " + buildingQueue.size() + "\nlvl: "+ buildings.get(type).getLvl());
        if (availableBuildings.contains(type) &&
                storage.hasEnouthResources(costsOfNext(type)) &&
                buildingQueue.size() < MAX_QUEUE_LENGTH &&
                buildings.get(type).getLvl() < type.getMaxLvl()) {
            return true;
        }
        throw new NotBuildableException();
    }

    public void updateAvailableBuildings(List<ETech> techs) {
        availableBuildings.clear();
        for (EBuilding b : buildings.keySet()) {
            if (fulfillReqs(b, techs) && !availableBuildings.contains(b)) {
                availableBuildings.add(b);
            }
        }
    }

    private boolean fulfillReqs(EBuilding b, List<ETech> techs) {
        if (techs.containsAll(b.getTechReqs())) {
            for (EBuilding type : b.getBuildingReqs().keySet()) {
                if (!buildings.keySet().contains(type)) {
                    return false;
                }
                if (buildings.get(type).getLvl() < b.getBuildingReqs().get(type)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Data Lookup Debug
    public HashMap<EResource, Double> costsOfNext(EBuilding type) {
        int lvl = buildings.get(type).getLvl();
        for (EBuilding b : buildingQueue) {
            if (b.equals(type)) {
                lvl++;
            }
        }
        return costsOf(type, lvl + 1);
    }

    public HashMap<EResource, Double> costsOf(EBuilding type, int lvl) {
        return type.getCosts(lvl);
    }

    public double durationOf(EBuilding type, int lvl) {
        return (type.getDuration(lvl) / speed);
    }


    // Getter/ Setter

    public int getLvlOf(EBuilding type) {
        return buildings.get(type).getLvl();
    }


    public double getQueueDuration() {
        double duration = 0;
        Map<EBuilding, Integer> queueLvlCounter = new HashMap<>();
        for (EBuilding b : buildings.keySet()) {
            queueLvlCounter.put(b, 0);
        }
        duration += remainingDuration;
        for (int i = 1; i < buildingQueue.size(); i++) {
            EBuilding b = buildingQueue.get(i);
            queueLvlCounter.put(b, queueLvlCounter.get(b) + 1);
            int lvl = buildings.get(b).getLvl() + queueLvlCounter.get(b);
            duration += durationOf(b, lvl);
        }
        return duration;
    }

    public void addBuildingBoost(double amount) {
        buildingBoost+=amount;
        computeSpeed();
    }

    public void addTechBoost(double amount) {
        techBoost+=amount;
        computeSpeed();
    }

    private void computeSpeed(){
            speed = 1.0*buildingBoost*techBoost;
    }

    public Set<EBuilding> getAvailableBuildings() {
        return availableBuildings;
    }

    public List<EBuilding> getBuildingQueue() {
        return buildingQueue;
    }

    public Map<EBuilding, Building> getBuildings() {
        return buildings;
    }

    public double getRemainingDuration() {
        return remainingDuration;
    }
}
