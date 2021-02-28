package com.meli.mutants.persistence.repository;

import com.meli.mutants.persistence.entities.MutantAnalysis;
import org.springframework.data.repository.CrudRepository;

public interface MutantAnalysisRepository extends CrudRepository<MutantAnalysis, String> {

    public int countMutantAnalysesByIsMutantIsFalse();

    public int countMutantAnalysesByIsMutantIsTrue();

}
