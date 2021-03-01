package com.meli.mutants.service;

import com.meli.mutants.controller.dto.MutantAnalysisStatsResponse;
import com.meli.mutants.model.Exception.InvalidDataException;
import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to provide stats from the mutant analysis.
 */
@Slf4j
@Service
public class MutantStatsService {

    /**
     * Repository of the mutants analysis.
     */
    private final MutantAnalysisRepository mutantAnalysisRepository;

    @Autowired
    public MutantStatsService(MutantAnalysisRepository mutantAnalysisRepository) {

        this.mutantAnalysisRepository = mutantAnalysisRepository;
    }

    /**
     * Gets the stats of the mutants analysis
     *
     * @return the stats of the mutants analyzed.
     */
    public MutantAnalysisStatsResponse getAnalysisStats(){

        int mutantAmount = mutantAnalysisRepository.countMutantAnalysesByIsMutantIsTrue();
        int humanAmount = mutantAnalysisRepository.countMutantAnalysesByIsMutantIsFalse();

        if (humanAmount == 0) {

            log.warn("Tried to get stats but the amount of humans was Zero(Division by zero)");
            throw new InvalidDataException("There is no suitable data to establish a ratio since human count is Zero");
        }

        return new MutantAnalysisStatsResponse(mutantAmount, humanAmount);

    }

}
