package ir.mofid.repeatedwords;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mofid.repeatedwords.requests.RepeatedListRequest;
import ir.mofid.repeatedwords.response.RepeatedListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private RepeatedService service;

    @Autowired
    private Controller controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/v1/repeated_list")
    void testGetCorrectResponse() throws Exception {
        RepeatedListRequest request = new RepeatedListRequest(List.of(2, 3, 1, 5, 4, 3, 2, 1));

        mockMvc.perform(post("/api/v1/repeated_list")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list").isArray())
                .andExpect(jsonPath("$.list", hasSize(3)))
                .andExpect(jsonPath("$.list", hasItem(1)))
                .andExpect(jsonPath("$.list", hasItem(2)))
                .andExpect(jsonPath("$.list", hasItem(3)));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
