package com.meli.mutants.service;

import com.meli.mutants.persistence.entities.MutantAnalysis;
import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import com.meli.mutants.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.meli.mutants.util.HashingUtil.createHash;

/**
 * Service that analyzes dna in order to find if dna is mutant or not.
 */
@Service
public class DnaAnalyzerService {

    /**
     * The Dna analyzer used to validate if a dna sequence is mutant or not.
     */
    private final DnaAnalyzer dnaAnalyzer;

    /**
     * Repository of the mutants analysis.
     */
    private final MutantAnalysisRepository mutantAnalysisRepository;

    @Autowired
    public DnaAnalyzerService(DnaAnalyzer dnaAnalyzer, MutantAnalysisRepository mutantAnalysisRepository) {
        this.dnaAnalyzer = dnaAnalyzer;
        this.mutantAnalysisRepository = mutantAnalysisRepository;
    }

    /**
     * Analyzes a dna sequence in order to find if is a mutant or not
     *
     * @param dnaSequence the dna sequence to be analyzed.
     * @return true if is mutant false if it is not.
     */
    public boolean analyzeDnaSequence(String[] dnaSequence){

        if(!ArrayUtil.isSquareMatrix(dnaSequence)) {

            throw new IllegalArgumentException("The given matrix was not square");
        }
        boolean analysisResult = dnaAnalyzer.isMutant(dnaSequence);
        mutantAnalysisRepository.save(new MutantAnalysis(createHash(Arrays.toString(dnaSequence)), analysisResult));

        return analysisResult;
    }
}
