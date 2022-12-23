package org.iogame.model.player;

import org.iogame.model.research.ETech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {



    @Test
    void shouldDevelopTech(){
        Team t1 = new Team("Team1");
        Player player = new Player("caro",t1);
        player.develop(ETech.AI);
        assertEquals(true,player.getTechManager().getdevelopedTech().contains(ETech.AI));
    }
}
