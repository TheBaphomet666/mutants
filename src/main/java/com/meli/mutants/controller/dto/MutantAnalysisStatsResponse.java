package com.meli.mutants.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MutantAnalysisStatsResponse {

    @JsonProperty("count_mutant_dna")
    private int numberOfMutants;

    @JsonProperty("count_human_dna")
    private int numberOfHumans;

    private double ratio;


    public MutantAnalysisStatsResponse(int numberOfMutants, int numberOfHumans) {
        this.numberOfMutants = numberOfMutants;
        this.numberOfHumans = numberOfHumans;
        this.ratio = numberOfMutants/ (double) numberOfHumans;
    }

}
