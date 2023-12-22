package org.xliu.cs.algs_ds.algs.hash;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

/**
 * Fowler_Noll_Vo_hash 1
 * <p>
 * FNV hashes are designed to be fast while maintaining a low collision rate.
 * The FNV speed allows one to quickly hash lots of data while maintaining a reasonable collision rate.
 */
@ClassNote("FNV1_32_Hash")
public class FNV1_32_Hash implements Hash {

    private static final long FNV_32_INIT = 2166136261L;
    private static final long FNV_32_PRIME = 16777619;

    @Override
    public int hash(byte[] bytes, int offset, int len) {
        long rv = FNV_32_INIT;
        for (int i = offset; i < len; i++) {
            rv *= FNV_32_PRIME;
            rv ^= bytes[i];
        }
        return (int) rv;
    }
}
