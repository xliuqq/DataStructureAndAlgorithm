package com.xliu.lifelearning.cs.algs.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5-based hash algorithm used by ketama.
 * <p>
 * md5的长度，默认为128bit，16 byte.
 */
public class KetamaHash extends Hash {

    @Override
    public int hash(byte[] bytes, int offset, int len) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        md5.update(bytes, offset, len);
        byte[] bKey = md5.digest();
        // 只取最后四位作为 ?
        return (bKey[3] & 0xFF) << 24 | (bKey[2] & 0xFF) << 16
                   | (bKey[1] & 0xFF) << 8 | bKey[0] & 0xFF;
    }
}
