package org.iogame.core;

import org.iogame.model.player.Player;

import java.util.Optional;

public abstract class GameObject {

    private final Id id;
    private final String name;

    protected double x;
    protected double y;

    protected Optional<Player> owner;

    public GameObject() {
        this(0.0D, 0.0D);
    }


    public GameObject(double x, double y) {
        this.id = Id.generateForClass(getClass());
        this.name = String.format("%s (%s)", getClass().getSimpleName(), id);

        this.x = x;
        this.y = y;

        this.owner = Optional.empty();
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

    public Optional<Player> getOwner() {
        return this.owner;
    }

    public void setOwner(Player player) {
        this.owner = Optional.of(player);
    }

    public boolean isOwnedBy(Player player) {
        return this.owner.map(owner -> owner.equals(player)).orElse(false);
    }

    public abstract void update(long delta);
}
