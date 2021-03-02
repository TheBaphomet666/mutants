package com.meli.mutants.service;

import com.meli.mutants.model.Exception.InvalidDataException;
import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DnaAnalyzerServiceTest {

    @Mock
    private MutantAnalysisRepository mutantAnalysisRepository;

    @Mock
    private DnaAnalyzer dnaAnalyzer;

    private DnaAnalyzerService dnaAnalyzerService;

    @BeforeEach
    public void setUp(){

        this.dnaAnalyzerService = new DnaAnalyzerService(dnaAnalyzer, mutantAnalysisRepository);
    }

    @Test
    void testAnalyzeDnaSequence_whenGivenMatrixIsNotSquare_thenThrowIllegalArgumentException() {

        var notSquareMatrix = new String[]{"PEP", "PA"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dnaAnalyzerService.analyzeDnaSequence(notSquareMatrix);
        });

        assertEquals("The given matrix was not square", exception.getMessage());
    }
}
