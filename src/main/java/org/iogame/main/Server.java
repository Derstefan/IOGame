package org.iogame.main;

import org.iogame.core.Id;
import org.iogame.model.player.Player;
import org.jetbrains.annotations.TestOnly;
import org.springframework.stereotype.Component;

import java.util.*;

public final class Server {

    private static final Server server = new Server();
    public static Server getInstance() {
        return server;
    }

    private final Map<Id, Game> games;

    private Server() {
        this.games = new HashMap<>();
    }

    /**
     * Creates a new game and adds it to the `games` map.
     * This will later be called by a controller via rest request.
     *
     * @param name Name of the game instance
     */
    public Id createGame(String name) {
        Game game = new Game(name);
        Id uuid = Id.generate();
        this.games.put(uuid, game);
        game.start();
        return uuid;
    }

    /**
     * Adds a player to a running game.
     */
    public void joinGame(Player player, Id gameId) {
        if (games.containsKey(gameId)) {
            getGameById(gameId).join(player);
        }
        throw new IllegalArgumentException(String.format("Game with id %s is not listed.", gameId));
    }

    public Collection<Game> getGames() {
        return this.games.values();
    }

    public Game getGameById(Id gameId) {
        return this.games.get(gameId);
    }

    public int getGameNumbers(){
        return  games.size();
    }

    public Set<Id> getGameIDs(){
        return games.keySet();
    }

    @TestOnly
    void clearGames() {
        this.games.clear();
    }


}
