package com.meli.mutants.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsecutiveDnaAnalyzerTest {

    private final ConsecutiveDnaAnalyzer consecutiveDnaAnalyzer = new ConsecutiveDnaAnalyzer();

    @Test
    @DisplayName("Test when a given dna sequence is mutant then should return true")
    void testIsMutant_whenTheDnaSequenceIsAMutant_ThenReturnTrue() {

        // Arrange
        String[] mutantDnaSequence = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(mutantDnaSequence);

        //Assert
        assertTrue(isMutant);
    }

    @Test
    @DisplayName("Test when a given dna sequence is not mutant then should return false")
    void testIsMutant_whenTheDnaSequenceIsNotAMutant_ThenReturnFalse() {

        // Arrange
        String[] humanDnaSequence = {"GTGCAA","CAGTGC","TTATGT","AGAAGG","CTCCTA","TCACTG"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(humanDnaSequence);

        //Assert
        assertFalse(isMutant);
    }

    @Test
    @DisplayName("Test when a given dna sequence is has one consecutive sequence then should return false")
    void testIsMutant_whenTheDnaSequenceIsNotAMutantButHaveOneConsecutiveSequence_ThenReturnFalse() {

        // Arrange
        String[] humanDnaSequence = {"GTAAAA","CAGTGC","TTATGT","AGAAGG","CTCCTA","TCACTG"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(humanDnaSequence);

        //Assert
        assertFalse(isMutant);
    }

    @Test
    @DisplayName("Test when a given dna sequence is mutant on rows then should return true")
    void testIsMutant_whenTheDnaSequenceIsAMutantOnRows_ThenReturnTrue() {

        // Arrange
        String[] mutantDnaSequence = {"GTAAAA","CAGTGC","TTTTGT","AGAAGG","CTCCTA","TCACTG"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(mutantDnaSequence);

        //Assert
        assertTrue(isMutant);
    }

    @Test
    @DisplayName("Test when a given dna sequence is mutant on columns then should return true")
    void testIsMutant_whenTheDnaSequenceIsAMutantOnColumns_ThenReturnTrue() {

        // Arrange
        String[] mutantDnaSequence = {"GTAATA","GAGTGC","GTATGA","GGAAGA","CTCCTA","TCACTA"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(mutantDnaSequence);

        //Assert
        assertTrue(isMutant);
    }

    @Test
    @DisplayName("Test when a given dna sequence is mutant on diagonals then should return true")
    void testIsMutant_whenTheDnaSequenceIsAMutantOnDiagonals_ThenReturnTrue() {

        // Arrange
        String[] mutantDnaSequence = {"TTAATA","AGTTGC","GCGTGA","GAGGTC","CTCCTT","TCACCA"};

        //Act
        boolean isMutant = consecutiveDnaAnalyzer.isMutant(mutantDnaSequence);

        //Assert
        assertTrue(isMutant);
    }
}
