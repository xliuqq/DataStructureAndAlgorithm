package com.xliu.lifelearning.cs.algs.hash;

public class NativeHash extends Hash {

    @Override
    public int hash(byte[] bytes, int offset, int len) {
        if (bytes == null) { return 0; }

        int result = 1;
        for (int i = offset; i < len; i++) {
            result = 31 * result + bytes[i];
        }

        return result;
    }
}
