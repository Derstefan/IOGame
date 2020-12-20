package org.iogame.controller;

import org.iogame.core.Id;
import org.iogame.main.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/main")
public class ServerController {

    private final int maxGames = 3;


    @Autowired
    private Server server;

    @RequestMapping("/numberofgames")
    public String getNumber() {
        return ""+server.getGameNumbers();
    }

    @RequestMapping("/newgame")
    public String createGame() {
        if(server.getGameNumbers()<maxGames) {
            Id id = server.createGame("game" + server.getGameNumbers());
            return "http://localhost:8080/game/map/planets/"+id.toString()+"";
        }else {
            return "";
        }
    }

    @RequestMapping("/gameids")
    public List<String> games() {
        List<String> list = new ArrayList<>();
        for (Id uuid:server.getGameIDs()) {
            list.add(uuid.toString());
        }
        return list;
    }
}