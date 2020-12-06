package iogame.model.planet;

public class Planet {
    private double x;
    private double y;
    private Resource resource;
    private Storage storage;
    private BuildingManager buildingManager;

    public Planet(double x, double y) {
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
}
