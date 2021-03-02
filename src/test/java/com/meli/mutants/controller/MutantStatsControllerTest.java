package com.meli.mutants.controller;

import com.meli.mutants.controller.dto.MutantAnalysisStatsResponse;
import com.meli.mutants.model.Exception.InvalidDataException;
import com.meli.mutants.service.MutantStatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MutantStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantStatsService mutantStatsService;

    @Test
    void getAnalysisStats() throws Exception {

        doReturn(new MutantAnalysisStatsResponse(40, 100))
                .when(mutantStatsService).getAnalysisStats();

        var result = mockMvc.perform(get("/stats"));

        result.andExpect(status().is(HttpStatus.OK.value()));
        result.andExpect(content().json("{\n" +
                "    \"ratio\": 0.4,\n" +
                "    \"count_mutant_dna\": 40,\n" +
                "    \"count_human_dna\": 100\n" +
                "}"));
    }

    @Test
    void getAnalysisStats_whenHumanCountIsZero() throws Exception {

        doThrow(new InvalidDataException("There is no suitable data to establish a ratio since human count is Zero"))
                .when(mutantStatsService).getAnalysisStats();

        var result = mockMvc.perform(get("/stats"));

        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    void getAnalysisStats_whenUnExpectedError() throws Exception {

        doThrow(new RuntimeException("Internal Error"))
                .when(mutantStatsService).getAnalysisStats();

        var result = mockMvc.perform(get("/stats"));

        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

    }
}
