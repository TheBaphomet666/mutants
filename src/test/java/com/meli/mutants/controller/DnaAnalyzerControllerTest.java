package com.meli.mutants.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutants.controller.dto.MutantAnalysisRequest;
import com.meli.mutants.service.DnaAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DnaAnalyzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaAnalyzerService dnaAnalyzerService;

    @Test
    void analyzeDnaSequence_whenIsAMutant_ThenReturnHttpStatusOK() throws Exception {

        doReturn(true)
                .when(dnaAnalyzerService).analyzeDnaSequence(any());

        var result = mockMvc.perform(post("/mutant")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new MutantAnalysisRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"}))));

        result.andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void analyzeDnaSequence_whenIsNotAMutant_ThenReturnHttpStatusForbidden() throws Exception {

        doReturn(false)
                .when(dnaAnalyzerService).analyzeDnaSequence(any());

        var result = mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new MutantAnalysisRequest(new String[]{"GTGCAA","CAGTGC","TTATGT","AGAAGG","CTCCTA","TCACTG"}))));

        result.andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    void analyzeDnaSequence_whenMatrixIsNotSquare_ThenReturnHttpStatusBadRequest() throws Exception {

        doThrow(new IllegalArgumentException("The given matrix was not square"))
                .when(dnaAnalyzerService).analyzeDnaSequence(any());

        var result = mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new MutantAnalysisRequest(new String[]{"GTGCAA","CAGTGC","TTATGT","AGAAGG","CTCCTA"}))));

        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
        result.andExpect(content().string("The given matrix was not square"));

    }

    @Test
    void analyzeDnaSequence_whenInternalServerError_ThenReturnHttpStatusInternalServerError() throws Exception {

        doThrow(new RuntimeException("oooPs"))
                .when(dnaAnalyzerService).analyzeDnaSequence(any());

        var result = mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new MutantAnalysisRequest(new String[]{"GTGCAA","CAGTGC","TTATGT","AGAAGG","CTCCTA","CTCCTA"}))));

        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

    }

    @Test
    void analyzeDnaSequence_whenRequestIsInvalid_ThenReturnHttpStatusBadRequest() throws Exception {


        var result = mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new MutantAnalysisRequest(  ))));

        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    /**
     * Converts an object to a Json string
     *
     * @param obj the object
     * @return the Object as a JSON String
     */
    private String asJsonString(final Object obj) throws JsonProcessingException {

            return new ObjectMapper().writeValueAsString(obj);

    }
}
