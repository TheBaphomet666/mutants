package com.meli.mutants.controller;

import com.meli.mutants.model.dto.MutantAnalysisRequest;
import com.meli.mutants.service.DnaAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Dna analysis.
 */
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
    public ResponseEntity<?> analyzeDnaSequence(@RequestBody final MutantAnalysisRequest mutantAnalysisRequest){

        HttpStatus response = dnaAnalyzerService.analyzeDnaSequence(mutantAnalysisRequest.getDna()) ? HttpStatus.OK
                : HttpStatus.FORBIDDEN;

        return new ResponseEntity<>(response);
    }
}
