package com.meli.mutants.model.dto;

import lombok.Data;

/**
 * DTO for mutant analysis request.
 */
@Data
public class MutantAnalysisRequest {

    /**
     * The dna sequence to be analised.
     */
    private String[] dna;
}
