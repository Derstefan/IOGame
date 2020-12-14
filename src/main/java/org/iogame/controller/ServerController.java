package org.iogame.controller;

import org.iogame.main.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
public class ServerController {

    @Autowired
    private Server server;

    @RequestMapping("/games")
    public String getNumber() {
        return "number of games "+server.getGameNumbers();
    }

    @RequestMapping("/newgame")
    public String createGame() {
        System.out.println(server.createGame("game"+server.getGameNumbers()));

        return "created Game";
    }
}