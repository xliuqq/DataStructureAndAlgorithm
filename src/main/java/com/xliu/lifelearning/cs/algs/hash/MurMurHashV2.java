package com.xliu.lifelearning.cs.algs.hash;

/**
 * MurMurHash算法，是非加密HASH算法，性能很高，
 * 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
 * 等HASH算法要快很多，这个算法的碰撞率很低.
 * <p>
 * murmur hash 2.0.
 * 参考：https://github.com/tnm/murmurhash-java/blob/master/src/main/java/ie/ucd/murmur/MurmurHash.java
 */
public class MurMurHashV2 extends Hash {

    @Override
    public int hash(byte[] bytes, int offset, int len) {
        int m = 0x5bd1e995;
        int r = 24;

        int h = 0xf7ca7fd2 ^ len;

        int len_4 = len >> 2;

        for (int i = 0; i < len_4; i++) {
            int i_4 = offset + (i << 2);
            int k = bytes[i_4 + 3];
            k = k << 8;
            k = k | (bytes[i_4 + 2] & 0xff);
            k = k << 8;
            k = k | (bytes[i_4 + 1] & 0xff);
            k = k << 8;
            k = k | (bytes[i_4 + 0] & 0xff);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        // avoid calculating modulo
        int len_m = len_4 << 2;
        int left = len - len_m;

        if (left != 0) {
            len += offset;
            if (left >= 3) {
                h ^= (int) bytes[len - 3] << 16;
            }
            if (left >= 2) {
                h ^= (int) bytes[len - 2] << 8;
            }
            if (left >= 1) {
                h ^= (int) bytes[len - 1];
            }

            h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;

        return h;
    }
}
