package com.meli.mutants.controller;

import com.meli.mutants.controller.dto.MutantAnalysisRequest;
import com.meli.mutants.service.DnaAnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Rest controller for Dna analysis.
 */
@Slf4j
@RestController
public class DnaAnalyzerController {

    /**
     * The DnaAnalyzer service used to identify if a dna sequence is a mutant or not.
     */
    private final DnaAnalyzerService dnaAnalyzerService;

    @Autowired
    public DnaAnalyzerController(DnaAnalyzerService dnaAnalyzerService) {

        this.dnaAnalyzerService = dnaAnalyzerService;
    }

    /**
     * Endpoint to analyze a dna sequence
     *
     * @param mutantAnalysisRequest the analysis request
     * @return a HTTP status code OK if is mutant FORBIDDEN if is not.
     */
    @PostMapping("/mutant")
    public ResponseEntity<?> analyzeDnaSequence(@Valid @RequestBody final MutantAnalysisRequest mutantAnalysisRequest){

        try {

            HttpStatus response = dnaAnalyzerService.analyzeDnaSequence(mutantAnalysisRequest.getDna()) ? HttpStatus.OK
                    : HttpStatus.FORBIDDEN;

            return new ResponseEntity<>(response);
        } catch (Exception e){

            return buildErrorResponse(e);
        }
    }

    /**
     * Builds a response in case of error
     *
     * @param e the exception
     * @return the error response
     */
    private ResponseEntity<?> buildErrorResponse(Exception e) {

        if(e instanceof IllegalArgumentException) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {

            log.error("Internal server error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
