package com.meli.mutants.service;

public interface DnaAnalyzer {

    /**
     * This method identifies if a  given dna sequence is a mutant or not
     * if it is a mutant returns true, if is not a mutant returns false.
     *
     * @param dna the dna sequence.
     * @return true if is mutant false if is not.
     */
    public boolean isMutant(String[] dna);
}
