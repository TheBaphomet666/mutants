package com.meli.mutants.persistence.entities;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "mutants", name = "mutant_analysis")
@AllArgsConstructor
public class MutantAnalysis {

    @Id
    @Column(name = "dna_sequence", unique = true, nullable = false)
    private String dnaSequence;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;

    public MutantAnalysis() {

    }
}
