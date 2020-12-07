package org.iogame.model;

public abstract class GameObject {

    String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void update(double delta);
}
