package com.xliu.lifelearning.cs.algs.hash;

import java.util.zip.CRC32;

/**
 * CRC全称为 Cyclic Redundancy Check，又叫循环冗余校验。CRC是目前使用中最老的一种校验算法。
 */
public class Crc32Hash extends Hash {
    @Override
    public int hash(byte[] bytes, int offset, int len) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, offset, len);
        // crc32返回的本身就是32位
        return (int) crc32.getValue();
    }
}
