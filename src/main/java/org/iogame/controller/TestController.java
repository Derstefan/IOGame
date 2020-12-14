package org.iogame.controller;

import org.iogame.main.Game;
import org.iogame.main.Server;
import org.iogame.model.player.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

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
