package com.tictactoe.tictactoe;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicTacToeRestControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public  void testContext() {

    }

    @Test
    public void whenPlayer_thenCreateGame()
        throws Exception {

        mvc.perform(post("/api/game/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"First\"}"))
                .andExpect(status().isOk());
        }

    @Test
    public void givenCreateGame_whenGame_thenVerifyResponse() throws Exception {
        var mvcResult = mvc.perform(post("/api/game/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"First\"}"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }
    @Test
    public void whenWrongId_thenError()
            throws Exception {

        mvc.perform(post("/api/game/connect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"player\": {\n" +
                                "        \"name\": \"second\"\n" +
                                "    },\n" +
                                "    \"gameId\": \"3f283e87-8858-4a9f-9173-b997dbd64802\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPlayer_thenConnectRandomGame()
            throws Exception {
        mvc.perform(post("/api/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"First\"}"));

        mvc.perform(post("/api/game/connect/random")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Random\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGame_thenPlayGame()
            throws Exception {
        mvc.perform(post("/api/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"First\"}"));

        mvc.perform(post("/api/game/connect/random")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Random\"}"))
                .andExpect(status().isOk());
    }
}
