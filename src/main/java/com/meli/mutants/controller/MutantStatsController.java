package com.meli.mutants.controller;

import com.meli.mutants.model.Exception.InvalidDataException;
import com.meli.mutants.service.MutantStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Dna analysis.
 */
@RestController
public class MutantStatsController {

    /**
     * Service that provides stats from the dna analysis.
     */
    private final MutantStatsService mutantStatsService;

    @Autowired
    public MutantStatsController(MutantStatsService mutantStatsService) {

        this.mutantStatsService = mutantStatsService;
    }

    /**
     * Endpoint to retrieve stats from the mutant dna analysis
     * @return a {#MutantAnalysisStatsResponse} with the information
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getAnalysisStats(){

        try {

            return new ResponseEntity<>(mutantStatsService.getAnalysisStats(), HttpStatus.OK);
        } catch (Exception e) {
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

        if(e instanceof InvalidDataException) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
