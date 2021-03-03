package com.meli.mutants.persistence.repository;

import com.meli.mutants.persistence.entities.MutantAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MutantAnalysisRepositoryTest {

    @Autowired
    private MutantAnalysisRepository mutantAnalysisRepository;

    @Test
    void countMutantAnalysesByIsMutantIsFalse() {

        mutantAnalysisRepository.save(new MutantAnalysis("dnaSequence", false));
        var mutantCount = mutantAnalysisRepository.countMutantAnalysesByIsMutantIsFalse();
        assertEquals(1, mutantCount);
    }

    @Test
    void countMutantAnalysesByIsMutantIsTrue() {

        mutantAnalysisRepository.save(new MutantAnalysis("dnaSequence", true));
        var mutantCount = mutantAnalysisRepository.countMutantAnalysesByIsMutantIsTrue();
        assertEquals(1, mutantCount);
    }

    @Test
    void testSaveMutantAnalysis() {

        var mutantAnalysis = mutantAnalysisRepository.save(new MutantAnalysis("dnaSequence", true));

        assertNotNull(mutantAnalysis);
        assertAll("mutantAnalysis",
                () -> assertEquals("dnaSequence", mutantAnalysis.getDnaSequence()),
                () -> assertTrue(mutantAnalysis.isMutant()));
    }
}
