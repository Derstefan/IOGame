package org.iogame.controller.gamecontroller;

import org.iogame.core.Id;
import org.iogame.exceptions.NotBuildableException;
import org.iogame.main.Game;
import org.iogame.main.Server;
import org.iogame.model.data.EBuilding;
import org.iogame.model.data.EResource;
import org.iogame.model.fleet.Fleet;
import org.iogame.model.planet.Planet;
import org.iogame.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
This Rest Controller is for all actions a player can perform in game.
- Research
- Build/cancel building
- Build/cancel ships
- fleetactions (move, colonize)
- manage fleet
- general game actions (pause, surrender, chat,...)
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/{id}/{playerId}")
public class ActionController {

    @Autowired
    private Server server;


    @RequestMapping("/research/{eTech}")
    public String research(@PathVariable String id, @PathVariable String playerId, @PathVariable String eTech){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,check resource available -> research
        }
        return "";
    }


    //Building

    @RequestMapping("/build/{eBuilding}/in/{planetId}")
    public ResponseEntity<String> buildBuilding(@PathVariable String id, @PathVariable String playerId, @PathVariable String eBuilding, @PathVariable String planetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game == null) {
            return new ResponseEntity<>("Game doesn't exists.",HttpStatus.NOT_FOUND);
        }
        Player player= null;// TODO: player validation
        if(player == null){
            return new ResponseEntity<>("Player isn't valid.",HttpStatus.FORBIDDEN);
        }
        Planet planet = game.getPlanetById(Id.fromString(planetId));
        if(planet.getPlayer()!=player){
            return new ResponseEntity<>("It's not players planet.",HttpStatus.FORBIDDEN);
        }

        try{
            planet.build(EBuilding.valueOf(eBuilding));
        } catch (Exception e) {
            if(e instanceof NotBuildableException){
                return new ResponseEntity<>("Not ready to build.", HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<>("Unknown error.", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @RequestMapping("/cancelbuilding/{queueIndex}/in/{planetId}")
    public ResponseEntity<String> cancelBuilding(@PathVariable String id, @PathVariable String playerId, @PathVariable int queueIndex, @PathVariable String planetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game == null) {
            return new ResponseEntity<>("Game doesn't exists.",HttpStatus.NOT_FOUND);
        }
        Player player= null;// TODO: player validation
        if(player == null){
            return new ResponseEntity<>("Player isn't valid.",HttpStatus.FORBIDDEN);
        }
        Planet planet = game.getPlanetById(Id.fromString(planetId));
        if(planet.getPlayer()!=player){
            return new ResponseEntity<>("It's not players planet.",HttpStatus.FORBIDDEN);
        }

        try{
            planet.cancelBuilding(queueIndex);
        } catch (Exception e) {
             return new ResponseEntity<>("Unknown error.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @RequestMapping("/cancelship/{queueIndex}/in/{planetId}")
    public String cancelShip(@PathVariable String id, @PathVariable String playerId, @PathVariable String eShip, @PathVariable String planetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,check resource/ ship available -> build
            Planet planet = game.getPlanetById(Id.fromString(planetId));
        }
        return "";
    }

    @RequestMapping("/produce/{eShip}/in/{planetId}")
    public String buildShip(@PathVariable String id, @PathVariable String playerId, @PathVariable String eShip, @PathVariable String planetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,check resource/ ship available -> build
            Planet planet = game.getPlanetById(Id.fromString(planetId));
        }
        return "";
    }

    // FleetActions

    @RequestMapping("/move/{fleetId}/to/{planetId}")
    public String moveFleet(@PathVariable String id, @PathVariable String playerId, @PathVariable String fleetId , @PathVariable String planetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player, fleet not moving?,-> move
            Fleet fleet = game.getFleetById(Id.fromString(id));
            Planet planet = game.getPlanetById(Id.fromString(id));
            fleet.moveTo(planet);
        }
        return "";
    }

    @RequestMapping("/retreat/{fleetId}")
    public String retreatFleet(@PathVariable String id, @PathVariable String playerId,@PathVariable String fleetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player, fleet not already retreated -> retreat
            Fleet fleet = game.getFleetById(Id.fromString(id));
            //fleet.retreat();
        }
        return "";
    }

    @RequestMapping("/colonize/{fleetId}/")
    public String colonizePlanet(@PathVariable String id, @PathVariable String playerId, @PathVariable String fleetId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,fleet contains coloship?, player can colonize? -> build
            Fleet fleet = game.getFleetById(Id.fromString(id));
            //fleet.colonize();
        }
        return "";
    }

    // Ship/Resource Management
    @RequestMapping("/restore/{fromId}/{ships}/{toId}")
    public String restoreShips(@PathVariable String id, @PathVariable String playerId, @PathVariable String fromId,@PathVariable List<String> ships , @PathVariable String toId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,same place, enouth ships, (enouth cap?) -> restore
        }
        return "";
    }

    @RequestMapping("/restore/{fromId}/{resources}/{toId}")
    public String restoreResources(@PathVariable String id, @PathVariable String playerId, @PathVariable String fromId , @PathVariable Map<EResource, Double> resources , @PathVariable String toId){
        Game game = server.getGameById(Id.fromString(id));
        if (game != null) {
            // TODO: check player,same place, enouth ships, (enouth cap?) -> restore

        }
        return "";
    }
}