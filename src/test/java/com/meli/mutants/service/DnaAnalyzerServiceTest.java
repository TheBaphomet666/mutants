package com.meli.mutants.service;

import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void testAnalyzeDnaSequence_whenAnalyzingMutantDna_thenReturnAnalysisResult() {

        var dnaSequence = new String[]{"PE", "PA"};
        Mockito.when(dnaAnalyzer.isMutant(Mockito.any())).thenReturn(true);
        var result = dnaAnalyzerService.analyzeDnaSequence(dnaSequence);

        assertTrue(result);
    }

    @Test
    void testAnalyzeDnaSequence_whenAnalyzingHumanDna_thenReturnAnalysisResult() {

        var dnaSequence = new String[]{"PE", "PA"};
        Mockito.when(dnaAnalyzer.isMutant(Mockito.any())).thenReturn(false);
        var result = dnaAnalyzerService.analyzeDnaSequence(dnaSequence);

        assertFalse(result);
    }
}
