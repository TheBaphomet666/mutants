package com.meli.mutants.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsecutiveDnaAnalyzerTest {

    private final ConsecutiveDnaAnalyzer consecutiveDnaAnalyzer = new ConsecutiveDnaAnalyzer();

    @Test
    @DisplayName("")
    void testIsMutant_whenTheDnaSequenceIsAMutant_ThenReturnTrue() {

        String[] mutantDnaSequence = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(mutantDnaSequence);
        assertTrue(isMutant);

    }
}
