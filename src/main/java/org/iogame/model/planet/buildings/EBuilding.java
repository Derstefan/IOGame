package org.iogame.model.planet.buildings;

import org.iogame.model.data.EResource;
import org.iogame.model.planet.buildings.defense.BadAssCannon;
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
    CLASTER_MINE(() -> new ClasterMine(), 20),
    SEVIT_MINE(() -> new SevitMine(), 20),
    NEDRIL_MINE(() -> new NedrilMine(), 20),
    ONAMA_MINE(() -> new OnamaMine(), 10),
    JIVANA_MINE(() -> new JivanaMine(), 10),
    APOGHYT_MINE(() -> new ApoghytMine(), 10),
    CLASTER_STORAGE(() -> new ClasterStorage(), 10),
    SEVIT_STORAGE(() -> new SevitStorage(), 10),
    NEDRIL_STORAGE(() -> new NedrilStorage(), 10),
    // Fleet
    SHIPYARD(() -> new ShipYard(), 10),
    SPACESTATION(() -> new SpaceStation(), 1),
    //Defense
    SHIELGENERATOR(() -> new ShieldGenerator(), 10),
    BADASS_CANNON(() -> new BadAssCannon(), 100),    // lvl=number
    FABRIC(() -> new Fabric(), 10),// faster build
    UNIVERSITY(() -> new Fabric(), 10);

    private int maxLvl;

    //lvl -> cost
    private final HashMap<Integer, HashMap<EResource, Double>> costs = new HashMap<>();
    //lvl -> duration
    private final HashMap<Integer, Long> duration = new HashMap<>();

    //requirementss
    private final HashMap<EBuilding, Integer> buildingReqs = new HashMap<>();
    private final Set<ETech> techReqs = new HashSet<>();

    private InvokeConstructor invokeConstructor;

    private void init() {
        for (int i = 1; i <= maxLvl; i++) {
            this.costs.put(i, EResource.emptyContainer());
            this.duration.put(i, 1000L * i); //test
        }

        switch (this.name()) {
            case "CLASTER_MINE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "SEVIT_MINE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "NEDRIL_MINE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "ONAMA_MINE":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                techReqs.add(ETech.MINERALOGY);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "JIVANA_MINE":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                techReqs.add(ETech.MINERALOGY);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "APOGHYT_MINE":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                techReqs.add(ETech.MINERALOGY);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "CLASTER_STORAGE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "SEVIT_STORAGE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "NEDRIL_STORAGE":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "SHIPYARD":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "SPACESTATION":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "SHIELGENERATOR":
                techReqs.add(ETech.DEFENSE);
                buildingReqs.put(EBuilding.UNIVERSITY, 3);
                buildingReqs.put(EBuilding.FABRIC, 2);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "BADASS_CANNON":
                buildingReqs.put(EBuilding.UNIVERSITY, 2);
                buildingReqs.put(EBuilding.FABRIC, 1);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "FABRIC":
                buildingReqs.put(EBuilding.UNIVERSITY, 1);
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            case "UNIVERSITY":
                for (int i = 1; i <= maxLvl; i++) {
                    costs.get(i).put(EResource.SEVIT, 2.0 * i);
                    costs.get(i).put(EResource.NEDRIL, 1.1 * i);
                }
            default:
        }
    }

    private EBuilding(InvokeConstructor invokeConstructor, int maxLvl) {
        this.invokeConstructor = invokeConstructor;
        this.maxLvl = maxLvl;


        init();
    }

    public HashMap<EResource, Double> getCosts(int lvl) {
        return costs.get(lvl);
    }

    public long getDuration(int lvl) {
        return duration.get(lvl);
    }

    @FunctionalInterface
    private interface InvokeConstructor {
        Building apply();
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