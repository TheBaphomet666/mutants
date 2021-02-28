package com.meli.mutants.service;

import com.meli.mutants.persistence.entities.MutantAnalysis;
import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DnaAnalyzerService {

    /**
     * The Dna analyzer used to validate if a dna sequence is mutant or not.
     */
    private final DnaAnalyzer dnaAnalyzer;

    private final MutantAnalysisRepository mutantAnalysisRepository;

    @Autowired
    public DnaAnalyzerService(DnaAnalyzer dnaAnalyzer, MutantAnalysisRepository mutantAnalysisRepository) {
        this.dnaAnalyzer = dnaAnalyzer;
        this.mutantAnalysisRepository = mutantAnalysisRepository;
    }

    public boolean analyzeDnaSequence(String[] dnaSequence){

        boolean analysisResult = dnaAnalyzer.isMutant(dnaSequence);
        mutantAnalysisRepository.save(new MutantAnalysis(Arrays.toString(dnaSequence), analysisResult)); //TODO IMPLEMENT A MORE EFFICIENT WAY OF ID SINCE THERE CAN BE REALLY BIG MATRIX

        return analysisResult;
    }
}
