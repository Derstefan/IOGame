package org.iogame.controller;

import org.iogame.main.Server;
import org.iogame.model.fleet.Fleet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/{id}/fleet")
public class FleetController {

    @Autowired
    private Server server;

    @RequestMapping("/fleets")
    public List<Fleet> getFleets(@PathVariable String id) {
        //for easy developing
        if(id.equals("1")){
            UUID uuid = server.getGameUUIDs().iterator().next();
            id=uuid.toString();
        }
        List<Fleet> fleets = server.getGameById(UUID.fromString(id)).getFleets();
        return fleets;
    }


}