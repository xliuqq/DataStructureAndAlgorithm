package com.xliu.cs.algs.index;

/**
 * 生产级别的Bloom Filter，参考Google guava的Bloom Filter
 */
public class BloomFilter {
    /** Hash函数的个数 */
    private int k;
    /** Hash函数的个数 */
    private int bitsPerKey;
    /** bit的总长度 */
    private int bitLen;
    /** Bloom filter的结果数据 */
    private byte[] result;

    public BloomFilter(int k, int bitsPerKey) {
        this.k = k;
        this.bitsPerKey = bitsPerKey;
    }

    private static int hash(byte[] key) {
        if (key == null) { return 0; }
        int h = 1;
        for (int i = 0; i < key.length; i++) {
            h = (h << 5) + h + key[i];
        }
        return h;
    }

    /**
     * 根据输入的key，生成 bloom filter
     */
    public byte[] generate(byte[][] keys) {
        assert keys != null;
        bitLen = keys.length * bitsPerKey;
        // align the bitLen.
        bitLen = ((bitLen + 7) / 8) << 3;
        bitLen = Math.max(bitLen, 64);
        result = new byte[bitLen >> 3];
        for (int i = 0; i < keys.length; i++) {
            assert keys[i] != null;
            // Note：这里是通过一个hash函数生成k个值，理论的误判率？
            int h = hash(keys[i]);
            for (int t = 0; t < k; t++) {
                // 防止h为负数，第一次取模后结果为负数
                int idx = (h % bitLen + bitLen) % bitLen;
                result[idx / 8] |= (1 << (idx % 8));
                // 偏移量
                int delta = (h >> 17) | (h << 15);
                h += delta;
            }
        }
        return result;
    }

    public boolean contains(byte[] key) {
        assert result != null;
        int h = hash(key);
        for (int t = 0; t < k; t++) {
            int idx = (h % bitLen + bitLen) % bitLen;
            if ((result[idx / 8] & (1 << (idx % 8))) == 0) {
                return false;
            }
            int delta = (h >> 17) | (h << 15);
            h += delta;
        }
        return true;
    }
}
