package org.iogame.model.planet;

import org.iogame.model.GameObject;

public class Planet extends GameObject {
    private double x;
    private double y;
    private Resource resource;
    private Storage storage;
    private BuildingManager buildingManager;

    Planet(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
        this.resource = new Resource();
        this.storage = new Storage();
        this.buildingManager = new BuildingManager(storage);
    }

    public void loop(double delta) {
        buildingManager.loop(delta);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void update(double delta) {

    }
}
