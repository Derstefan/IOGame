package org.iogame.core;

public abstract class GameObject {

    private final Id id;
    private final String name;

    public GameObject() {
        this.id = Id.generateForClass(getClass());
        this.name = String.format("%s (%s)", getClass().getSimpleName(), id);
    }

    public Id getId() {
        return this.id;
    }

    public abstract void update(long delta);
}
