package com.xliu.lifelearning.cs.algs.hash;


/**
 * Fowler_Noll_Vo_hash 1A
 */
public class FNV1A_32_Hash extends Hash {

    private static final long FNV_32_INIT = 2166136261L;
    private static final long FNV_32_PRIME = 16777619;

    @Override
    public int hash(byte[] bytes, int offset, int len) {
        long rv = FNV_32_INIT;
        for (int i = offset; i < len; i++) {
            rv ^= bytes[i];
            rv *= FNV_32_PRIME;
        }
        return (int) rv;
    }
}
