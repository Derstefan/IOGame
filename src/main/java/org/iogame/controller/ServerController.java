package org.iogame.controller;

import org.iogame.main.Game;
import org.iogame.main.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            UUID id = server.createGame("game" + server.getGameNumbers());
            return "http://www.iogame.de/games/"+id.toString()+"/";
        }else {
            return "";
        }

    }

    @RequestMapping("/gameids")
    public List<String> games() {
        List<String> list = new ArrayList<>();
        for (UUID uuid:server.getGameUUIDs()) {
            list.add(uuid.toString());
        }
        return list;
    }
}