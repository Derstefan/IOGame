package org.iogame.controller;

import org.iogame.core.Id;
import org.iogame.main.Server;
import org.iogame.model.player.Player;
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
@RequestMapping("/{id}/game")
public class GameController {

    @Autowired
    private Server server;

    @RequestMapping("/teams")
    public List<Team>  getTeams(@PathVariable String id) {
        List<Team> teams = server.getGameById(Id.fromString(id)).getTeams();
        return teams;
    }

    @RequestMapping("/players")
    public List<Player>  getPlayers(@PathVariable String id) {
        List<Player> players = server.getGameById(Id.fromString(id)).getPlayers();
        return players;
    }
}