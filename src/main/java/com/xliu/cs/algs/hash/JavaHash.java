package com.xliu.cs.algs.hash;

/**
 * Java 默认的Hash算法，其不适用于一致性Hash中的Hash算法。
 */
public class JavaHash implements Hash {
    @Override
    public int hash(byte[] bytes, int offset, int len) {
        if (bytes == null) { return 0; }

        int result = 1;
        // Java String 的 Hash 算法（UTF16）按照Char为单位计算
        for (int i = offset; i < len; i++) {
            result = 31 * result + bytes[i];
        }

        return result;
    }
}
