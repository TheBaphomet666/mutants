package com.meli.mutants.service;

import com.meli.mutants.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;


/**
 * Class that validates a Dna sequence to validate if is mutant using the method of
 * consecutive Codons.
 *
 */
@Slf4j
public class ConsecutiveDnaAnalyzer implements DnaAnalyzer {

    /**
     * The amount that identifies the number of consecutive DNA repetitions
     * that makes a mutant.
     */
    public static int MUTANT_COEFFICIENT = 2;

    /**
     * The amount of consecutive codons that identifies a potential mutant.
     */
    public static int MUTANT_CODON_CONSECUTIVE = 4;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutant(final String[] dna) {

        ArrayList<ArrayList<String>> dnaMatrix = ArrayUtil.to2DArrayList(dna);
        int mutantMatches = 0;

        for (int i = 0; i < dnaMatrix.size() && mutantMatches < MUTANT_COEFFICIENT; i++) {

            var row = dnaMatrix.get(i);
            for (int j = 0; j < row.size() && mutantMatches < MUTANT_COEFFICIENT; j++) {

                if(row.size() - j >= MUTANT_CODON_CONSECUTIVE) {

                    mutantMatches += isAMutantRowSequence(row, j) ? 1 : 0;
                }

                if(dnaMatrix.size() - i >= MUTANT_CODON_CONSECUTIVE) {

                    mutantMatches += isAMutantColumnSequence(dnaMatrix, i, j) ? 1 : 0;
                }

                if (row.size() - j >= MUTANT_CODON_CONSECUTIVE && dnaMatrix.size() - i >= MUTANT_CODON_CONSECUTIVE) {

                    mutantMatches += isMutantDiagonalSequence(dnaMatrix, i, j) ? 1 : 0;
                }

            }
        }

        return mutantMatches >= MUTANT_COEFFICIENT;
    }


    /**
     * Validates if a row from a given index is a mutant sequence.
     *
     * @param row the row to be validated.
     * @param index the starting index.
     * @return true if is mutant false if is not.
     */
    private boolean isAMutantRowSequence(final ArrayList<String> row, final int index) {


        for (int k = index + 1; k < index + MUTANT_CODON_CONSECUTIVE; k++){

            if (!row.get(index).equals(row.get(k))) {

                return false;
            }
        }

        return true;
    }

    /**
     * Validates if the column on the given indexes is a mutant sequence.
     *
     * @param dnaMatrix the matrix with the codons.
     * @param rowIndex the starting vertical index.
     * @param columnIndex the starting horizontal index.
     * @return true if is a mutant column false if it is not.
     */
    private boolean isAMutantColumnSequence(final ArrayList<ArrayList<String>> dnaMatrix,
                                            final int rowIndex,
                                            final int columnIndex) {


        String sample = dnaMatrix.get(rowIndex).get(columnIndex);

        for (int i = rowIndex+1; i < rowIndex + MUTANT_CODON_CONSECUTIVE; i++){

            if (!sample.equals(dnaMatrix.get(i).get(columnIndex))) {

                return false;
            }
        }

        return true;
    }

    /**
     * Validates if the diagonal on the given indexes is a mutant sequence.
     *
     * @param dnaMatrix the matrix with the codons.
     * @param columnIndex the starting horizontal index.
     * @param rowIndex the starting vertical index.
     * @return true if is a mutant diagonal false if it is not.
     */
    private boolean isMutantDiagonalSequence(final ArrayList<ArrayList<String>> dnaMatrix,
                                             final int columnIndex,
                                             final int rowIndex) {


        String sample = dnaMatrix.get(rowIndex).get(columnIndex);

        for (int i = 1; i < MUTANT_CODON_CONSECUTIVE; i++){

            if (!sample.equals(dnaMatrix.get(rowIndex + i).get(columnIndex + i))) {

                return false;
            }
        }

        return true;
    }
}
