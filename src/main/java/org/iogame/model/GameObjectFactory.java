package org.iogame.model;

import org.iogame.main.Game;

public abstract class GameObjectFactory<T extends GameObject> {

    protected Game game;

    public GameObjectFactory(Game game) {
        this.game = game;
    }
}
