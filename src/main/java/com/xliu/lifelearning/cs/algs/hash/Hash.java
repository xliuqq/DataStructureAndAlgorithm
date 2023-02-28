package com.xliu.lifelearning.cs.algs.hash;

public abstract class Hash {


    abstract public int hash(byte[] bytes, int offset, int len);

    public int hash(String key) {
        return hash(key.getBytes());
    }

    public int hash2Positive(String key) {
        int value = hash(key.getBytes());
        if (value < 0) {
            return -value;
        }
        return value;
    }

    public final int hash(byte[] bytes) {
        return hash(bytes, 0, bytes.length);
    }

}
