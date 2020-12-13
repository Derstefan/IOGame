package org.iogame.model.battle;

import org.iogame.model.planet.Planet;

public class Battle {
    private Planet planet;

    public Battle(Planet planet) {
        this.planet = planet;
        System.out.println("battle started");
    }


    public void update(long delta){

    }

}
