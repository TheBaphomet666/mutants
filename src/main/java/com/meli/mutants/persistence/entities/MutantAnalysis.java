package com.meli.mutants.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity that defines the result of a dna analysis.
 */
@Entity
@Table(schema = "mutants", name = "mutant_analysis")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MutantAnalysis {

    /**
     * the dna sequence analysed
     */
    @Id
    @Column(name = "dna_sequence", unique = true, nullable = false)
    private String dnaSequence;

    /**
     * flag that determines id it is a mutant.
     */
    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;

}
