package org.iogame.core;

public abstract class GameObject {

    private final Id id;
    private final String name;

    protected double x;
    protected double y;

    public GameObject() {
        this(0.0D, 0.0D);
    }


    public GameObject(double x, double y) {
        this.id = Id.generateForClass(getClass());
        this.name = String.format("%s (%s)", getClass().getSimpleName(), id);

        this.x = x;
        this.y = y;
    }

    public Id getId() {
        return this.id;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public abstract void update(long delta);
}
