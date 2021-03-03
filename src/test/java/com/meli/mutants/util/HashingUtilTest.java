package com.meli.mutants.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashingUtilTest {

    @Test
    @DisplayName("Test when creating hash must create a unique hash for the given string")
    void testCreateHash_whenGivenAString_shouldCreateAHash(){

        var string = "anyString";
        var firstHash = HashingUtil.createHash(string);
        var secondHash = HashingUtil.createHash(string);
        assertEquals(firstHash, secondHash);
    }

}
