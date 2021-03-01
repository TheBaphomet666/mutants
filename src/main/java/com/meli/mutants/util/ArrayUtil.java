package com.meli.mutants.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class for arrays
 */
public final class ArrayUtil {


    /**
     * Converts a array 2d array to a ArrayList
     *
     * @param array the array to be converted
     * @return a 2d arraylist
     */
    public static ArrayList<ArrayList<String>> to2DArrayList(final String[] array) {

        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();

        for (String s : array) {

            arrayList.add(new ArrayList<>(Arrays.asList(s.split(""))));
        }
        return arrayList;
    }

    /**
     * Verifies if a given array is a square matrix
     *
     * @param array the array to be verified
     * @return true if is square false if is not
     */
    public static boolean isSquareMatrix(final String[] array) {

        int n = array.length;

        for (String s : array) {
            if(s.length()!= n) {
                return false;
            }
        }
        return true;
    }
}
