package org.iogame.sync;

import org.iogame.model.battle.Battle;

public class BattleSync {

    private long id;

    private float x;
    private float y;

    public BattleSync(Battle battle) {
        //this.id = battle.getId();
        this.x=(float)battle.getPlanet().getX();
        this.y=(float)battle.getPlanet().getY();

    }

    public long getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
