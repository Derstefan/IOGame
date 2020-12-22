package org.iogame.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateNewGames() throws Exception {
        // number of games
        mvc.perform(MockMvcRequestBuilders.get("/main/numberofgames").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

        // create new game
        mvc.perform(MockMvcRequestBuilders.get("/main/newgame").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

        // get game ids
        mvc.perform(MockMvcRequestBuilders.get("/main/gameids").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"0\"]"));
    }
}