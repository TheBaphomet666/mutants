package com.meli.mutants.controller.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for mutant analysis request.
 */
@Getter
public class MutantAnalysisRequest {

    /**
     * The dna sequence to be analised.
     */
    @NotNull(message = "Dna sequence can not be null")
    private String[] dna;
}
