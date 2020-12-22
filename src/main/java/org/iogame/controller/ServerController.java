package org.iogame.controller;

import org.iogame.core.Id;
import org.iogame.main.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/main")
public class ServerController {

    private static final int MAX_GAMES = 3;

    @Autowired
    private Server server;

    @RequestMapping("/numberofgames")
    public String getNumber() {
        return "" + server.getGameNumbers();
    }

    @RequestMapping("/newgame")
    public String createGame() {
        if (server.getGameNumbers() < MAX_GAMES) {
            Id id = server.createGame("game" + server.getGameNumbers());
            return id.toString();
        } else {
            return "";
        }
    }

    @RequestMapping("/gameids")
    public List<String> games() {
        return server.getGameIDs().stream().map(Id::toString).collect(Collectors.toList());
    }
}