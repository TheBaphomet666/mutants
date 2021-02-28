package com.meli.mutants.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class ArrayUtil {


    public static ArrayList<ArrayList<String>> to2DArrayList(final String[] array) {

        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();

        for (String s : array) {

            arrayList.add(new ArrayList<>(Arrays.asList(s.split(""))));
        }
        return arrayList;
    }
}
