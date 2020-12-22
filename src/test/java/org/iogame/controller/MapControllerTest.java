package org.iogame.controller;

import org.iogame.JsonHelper;
import org.iogame.core.Id;
import org.iogame.main.Game;
import org.iogame.main.Server;
import org.iogame.sync.PlanetSync;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MapControllerTest {

    @Autowired
    private MockMvc mvc;

    static Id gameId;

    @BeforeAll
    static void setup() {
        gameId = Server.getInstance().createGame("test");
    }

    @Test
    void shouldGetPlanetsFromGame() throws Exception {
        Game game = Server.getInstance().getGameById(gameId);
        List<PlanetSync> planetSyncs = game.getPlanets().stream()
                .map(PlanetSync::new)
                .collect(Collectors.toList());
        var expectedContent = JsonHelper.serializeObject(planetSyncs);

        mvc.perform(MockMvcRequestBuilders.get(String.format("/%d/map/planets", gameId.getId())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

}