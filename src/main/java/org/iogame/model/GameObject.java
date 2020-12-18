package org.iogame.model;

import java.util.UUID;

public abstract class GameObject {

    private final UUID id;
    private final String name;

    public GameObject() {
        this.id = UUID.randomUUID();
        this.name = String.format("%s (%s)", getClass().getSimpleName(), id);
    }

    public UUID getId() {
        return this.id;
    }

    public abstract void update(long delta);
}
