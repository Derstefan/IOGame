package org.iogame.controller;

import org.iogame.core.Id;
import org.iogame.main.Server;
import org.iogame.model.planet.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/{id}/planet")
public class PlanetController {

    @Autowired
    private Server server;

    @RequestMapping("/planets")
    public List<Planet> getPlanets(@PathVariable String id) {
        List<Planet> planets = server.getGameById(Id.fromString(id)).getPlanets();

        return planets;
    }

    @RequestMapping("/planets/{planetId}")
    public Planet getPlanets(@PathVariable String id,@PathVariable String planetId) {
        Planet planet = server.getGameById(Id.fromString(id)).getPlanets().get(Integer.valueOf(planetId));

        return planet;
    }


}