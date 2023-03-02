package com.xliu.cs.algs.hash;

/***
 * hash 基类
 */
public interface Hash {

    int hash(byte[] bytes, int offset, int len);

    default int hash(String key) {
        return hash(key.getBytes());
    }

    default int hash2Positive(String key) {
        int value = hash(key.getBytes());
        if (value < 0) {
            return -value;
        }
        return value;
    }

    default int hash(byte[] bytes) {
        return hash(bytes, 0, bytes.length);
    }

}
