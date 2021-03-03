package com.meli.mutants.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * DTO for mutant analysis request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutantAnalysisRequest {

    /**
     * The dna sequence to be analised.
     */
    @NotNull(message = "Dna sequence can not be null")
    private String[] dna;
}
