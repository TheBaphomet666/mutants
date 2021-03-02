package com.meli.mutants.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilTest {

    @Test
    void testTo2DArrayList_whenGivenArray_thenReturnA2DArrayList() {

        var array = new String[]{"PEPE", "PACO"};
        var arrayList = new ArrayList<>();
        arrayList.add(new ArrayList<>(Arrays.asList("P","E","P","E")));
        arrayList.add(new ArrayList<>(Arrays.asList("P","A","C","O")));

        var resultArrayList = ArrayUtil.to2DArrayList(array);
        assertEquals(arrayList, resultArrayList);
    }

    @Test
    void testIsSquareMatrix_whenGivenSquareMatrix_thenReturnTrue() {

        var squareArray = new String[]{"PE", "PA"};
        assertTrue(ArrayUtil.isSquareMatrix(squareArray));
    }

    @Test
    void testIsSquareMatrix_whenGivenNotSquareMatrix_thenReturnFalse() {

        var notSquareArray = new String[]{"PEP", "PA"};
        assertFalse(ArrayUtil.isSquareMatrix(notSquareArray));
    }
}
