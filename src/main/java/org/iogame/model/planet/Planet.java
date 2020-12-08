package org.iogame.model.planet;

import org.iogame.model.enums.EBuilding;

public class Planet {
    private double x;
    private double y;
    private ResourceDeposit resourceDeposit;
    private Storage storage;
    private BuildingManager buildingManager;
    private MiningManager miningManager;

    public Planet(double x, double y) {
        this.x = x;
        this.y = y;
        this.resourceDeposit = new ResourceDeposit();
        this.storage = new Storage();
        this.buildingManager = new BuildingManager(storage,this);
        this.miningManager = new MiningManager(resourceDeposit,storage);
    }

    public void loop(double delta) {
        buildingManager.loop(delta);
        miningManager.loop(delta);
    }

    public boolean buildBuilding(EBuilding BuildingType){
        return buildingManager.startBuild(BuildingType);
    }

    public boolean destroyBuilding(EBuilding BuildingType){
        return buildingManager.destroy(BuildingType);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ResourceDeposit getResource() {
        return resourceDeposit;
    }

    public Storage getStorage() {
        return storage;
    }

    public ResourceDeposit getResourceDeposit() {
        return resourceDeposit;
    }

    public void setResourceDeposit(ResourceDeposit resourceDeposit) {
        this.resourceDeposit = resourceDeposit;
    }

    public BuildingManager getBuildingManager() {
        return buildingManager;
    }

    public void setBuildingManager(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
    }

    public MiningManager getMiningManager() {
        return miningManager;
    }

    public void setMiningManager(MiningManager miningManager) {
        this.miningManager = miningManager;
    }
}
