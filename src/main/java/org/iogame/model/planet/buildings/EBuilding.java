package org.iogame.model.planet.buildings;

import org.iogame.model.data.EResource;
import org.iogame.model.planet.buildings.defense.BadAssCannon;
import org.iogame.model.planet.buildings.defense.BigAssCannon;
import org.iogame.model.planet.buildings.defense.ShieldGenerator;
import org.iogame.model.planet.buildings.general.Fabric;
import org.iogame.model.planet.buildings.mines.*;
import org.iogame.model.planet.buildings.space.ShipYard;
import org.iogame.model.planet.buildings.space.SpaceStation;
import org.iogame.model.planet.buildings.storages.ClasterStorage;
import org.iogame.model.planet.buildings.storages.NedrilStorage;
import org.iogame.model.planet.buildings.storages.SevitStorage;
import org.iogame.model.research.ETech;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public enum EBuilding {

    // Economy
    CLASTER_MINE(() -> new ClasterMine(),20),
    SEVIT_MINE(() -> new SevitMine(),20),
    NEDRIL_MINE(() -> new NedrilMine(),20),
    ONAMA_MINE(() -> new OnamaMine(),10),
    JIVANA_MINE(() -> new JivanaMine(),10),
    APOGHYT_MINE(() -> new ApoghytMine(),10),

    CLASTER_STORAGE(() -> new ClasterStorage(),10),
    SEVIT_STORAGE(() -> new SevitStorage(),10),
    NEDRIL_STORAGE(() -> new NedrilStorage(),10),


    // Fleet
    SHIPYARD(()-> new ShipYard(),10),
    SPACESTATION(()-> new SpaceStation(),10),

    //Defense
    SHIELGENERATOR(()-> new ShieldGenerator(),10),
    BADASS_CANNON(()-> new BadAssCannon(),100),    // lvl=number
    BIGASS_CANNON(()-> new BigAssCannon(),100),    // lvl=number


    FABRIC(()-> new Fabric(),10),
    UNIVERSITY(()-> new Fabric(),10);

    private int maxLvl;

    //lvl -> cost
    private final HashMap<Integer, HashMap<EResource, Double>> costs = new HashMap<>();
    //lvl -> duration
    private final HashMap<Integer, Long> duration = new HashMap<>();

    //requirementss
    private final HashMap<EBuilding, Integer> buildingReqs = new HashMap<>();
    private final Set<ETech> techReqs = new HashSet<>();

    private InvokeConstructor invokeConstructor;

    @FunctionalInterface
    private interface InvokeConstructor {
        Building apply();
    }

    private EBuilding(InvokeConstructor invokeConstructor,int maxLvl) {
        this.invokeConstructor = invokeConstructor;
        this.maxLvl = maxLvl;
        switch (this.name()) {
            case "CLASTER_MINE":

            case "SEVIT_MINE":

            default:
        }
    }

    public HashMap<EResource, Double> getCosts(int lvl) {
        HashMap<EResource, Double> costs = new HashMap<>();
        for (EResource r : EResource.values()) {
            costs.put(r, 0.7);
        }
        return costs;
    }

    public long getDuration(int lvl) {
        return 5 * 1000;
    }

    public Building getInstance() {
        return invokeConstructor.apply();
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public HashMap<EBuilding, Integer> getBuildingReqs() {
        return buildingReqs;
    }

    public Set<ETech> getTechReqs() {
        return techReqs;
    }
}
