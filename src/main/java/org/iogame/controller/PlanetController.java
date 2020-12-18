package org.iogame.controller;

import org.iogame.main.Server;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/{id}/planet")
public class PlanetController {

    @Autowired
    private Server server;

    @RequestMapping("/planets")
    public List<Planet> getPlanets(@PathVariable String id) {
        //for easy developing
        if(id.equals("1")){
            UUID uuid = server.getGameUUIDs().iterator().next();
            id=uuid.toString();
        }

        List<Planet> planets = server.getGameById(UUID.fromString(id)).getPlanets();

        return planets;
    }

    @RequestMapping("/planets/{planetId}")
    public Planet getPlanets(@PathVariable String id,@PathVariable String planetId) {
        //for easy developing
        if(id.equals("1")){
            UUID uuid = server.getGameUUIDs().iterator().next();
            id=uuid.toString();
        }

        Planet planet = server.getGameById(UUID.fromString(id)).getPlanets().get(Integer.valueOf(planetId));

        return planet;
    }


}