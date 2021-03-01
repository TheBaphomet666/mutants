package com.meli.mutants.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Utility class for Hashing
 */
public final class HashingUtil {


    /**
     * Creates hash from a string
     *
     * @param string to be hashed
     * @return the hash
     */
    public static String createIdentifier(String string) {
        return Hashing
                .murmur3_32()
                .newHasher()
                .putString(string, Charsets.UTF_8)
                .hash().toString();

    }
}
