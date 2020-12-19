package org.iogame.controller;

import org.iogame.core.Id;
import org.iogame.main.Game;
import org.iogame.main.Server;
import org.iogame.model.battle.Battle;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.iogame.sync.BattleSync;
import org.iogame.sync.FleetSync;
import org.iogame.sync.PlanetSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/{id}/map")
public class MapController {

    @Autowired
    private Server server;

    @RequestMapping("/planets")
    public List<PlanetSync>  getPlanets(@PathVariable String id) {
        Game game = server.getGameById(Id.fromString(id));

        List<PlanetSync> planets=new ArrayList<>();
        for (Planet p:game.getPlanets()) {
            planets.add(new PlanetSync(p));
        }
        return planets;
    }

    @RequestMapping("/fleets")
    public List<FleetSync> getFleets(@PathVariable String id) {
        Game game = server.getGameById(Id.fromString(id));

        List<FleetSync> fleets =new ArrayList<>();
        for (Fleet f:game.getFleets()) {
            fleets.add(new FleetSync(f));
        }
        return fleets;
    }

    @RequestMapping("/battles")
    public List<BattleSync>  getBattles(@PathVariable String id) {
        Game game = server.getGameById(Id.fromString(id));

        List<BattleSync> battles =new ArrayList<>();
        for (Battle b:game.getBattles()) {
            battles.add(new BattleSync(b));
        }
        return battles;
    }

}