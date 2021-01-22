package org.iogame.model.planet;

import org.iogame.core.GameObject;
import org.iogame.model.battle.Battle;
import org.iogame.model.data.EBlueprint;
import org.iogame.model.data.EBuilding;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.fleet.ship.BlueprintManager;
import org.iogame.model.fleet.ship.ShipBuilder;
import org.iogame.model.player.Player;
import org.iogame.model.player.Team;

import java.util.ArrayList;
import java.util.List;

public class Planet extends GameObject {

    private double x;
    private double y;
    private ResourceDeposit resourceDeposit;
    private Storage storage;
    private BuildingManager buildingManager;
    private MiningManager miningManager;
    private ShipBuilder shipBuilder;

    // controlled player, null if its no one
    private Player player;


    private Battle battle = null;

    // fleets on/around this planet
    private List<Fleet> fleets = new ArrayList<>();

    public Planet(double x, double y, BlueprintManager blueprintManager) {
        this.x = x;
        this.y = y;
        this.resourceDeposit = new ResourceDeposit();
        this.storage = new Storage();
        this.buildingManager = new BuildingManager(storage, this);
        this.miningManager = new MiningManager(resourceDeposit, storage);
        this.shipBuilder = new ShipBuilder(blueprintManager.getBlueprints(), this);
        if(checkPeace()){
            battle = null;
        } else {
            battle = new Battle(this);
        }
    }

    @Override
    public void update(long delta) {
        buildingManager.update(delta);
        miningManager.update(delta);
        if (getBattle() != null) {
            //Battles
            getBattle().update(delta);
        }
    }


    public boolean checkPeace() {
        if (!fleets.isEmpty()) {
            Team t = fleets.get(0).getPlayer().getTeam();
            for (Fleet f : fleets) {

                if (!f.getPlayer().getTeam().equals(t)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean buildBuilding(EBuilding BuildingType) {
        return buildingManager.startBuild(BuildingType);
    }

    public boolean destroyBuilding(EBuilding BuildingType) {
        return buildingManager.destroy(BuildingType);
    }

    public boolean buildShip(EBlueprint eBlueprint){
        return false;
    }




    // Getter Setter

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public List<Fleet> getFleets() {
        return fleets;
    }

    public ResourceDeposit getResource() {
        return resourceDeposit;
    }

    public Storage getStorage() {
        return storage;
    }

    public ResourceDeposit getResourceDeposit() {
        return resourceDeposit;
    }

    public void setResourceDeposit(ResourceDeposit resourceDeposit) {
        this.resourceDeposit = resourceDeposit;
    }

    public BuildingManager getBuildingManager() {
        return buildingManager;
    }

    public void setBuildingManager(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
    }

    public MiningManager getMiningManager() {
        return miningManager;
    }

    public void setMiningManager(MiningManager miningManager) {
        this.miningManager = miningManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
