package com.meli.mutants.service;

import com.meli.mutants.model.Exception.InvalidDataException;
import com.meli.mutants.persistence.repository.MutantAnalysisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MutantStatsServiceTest {

    @Mock
    private MutantAnalysisRepository mutantAnalysisRepository;

    private MutantStatsService mutantStatsService;

    @BeforeEach
    public void setUp(){

        this.mutantStatsService = new MutantStatsService(mutantAnalysisRepository);
    }

    @Test
    void testGetAnalysisStats_whenStatsArePresent_thenReturnStats() {

        Mockito.when(mutantAnalysisRepository.countMutantAnalysesByIsMutantIsTrue())
                .thenReturn(40);
        Mockito.when(mutantAnalysisRepository.countMutantAnalysesByIsMutantIsFalse())
                .thenReturn(100);

        var analysisResponse = mutantStatsService.getAnalysisStats();

        assertNotNull(analysisResponse);
        assertAll("analysisResponse",
                () -> assertEquals(40, analysisResponse.getNumberOfMutants()),
                () -> assertEquals(100, analysisResponse.getNumberOfHumans()),
                () -> assertEquals(0.4, analysisResponse.getRatio()));
    }

    @Test
    void testGetAnalysisStats_whenThereAreZeroHumans_thenThrowInvalidDataException() {

        Mockito.when(mutantAnalysisRepository.countMutantAnalysesByIsMutantIsTrue())
                .thenReturn(40);
        Mockito.when(mutantAnalysisRepository.countMutantAnalysesByIsMutantIsFalse())
                .thenReturn(0);

        Exception exception = assertThrows(InvalidDataException.class, () -> {
            mutantStatsService.getAnalysisStats();
        });

        assertEquals("There is no suitable data to establish a ratio since human count is Zero",
                exception.getMessage());
    }
}
