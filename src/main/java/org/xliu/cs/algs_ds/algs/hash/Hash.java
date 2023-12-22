package org.xliu.cs.algs_ds.algs.hash;

import org.xliu.cs.projects.anno_for_doc.annotations.IgnoreNote;

/***
 * hash 基类
 */
@IgnoreNote
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
