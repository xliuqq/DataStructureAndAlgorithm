package org.xliu.cs.algs_ds.ds.index;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

/**
 * 生产级别的Bloom Filter，参考 Google guava的 Bloom Filter
 */
@ClassNote("布隆过滤器")
public class BloomFilter {
    /**
     * bit的总长度，根据 fpp 和插入次数计算
     */
    private long bitLen;
    /**
     * Hash函数的个数，根据 bitLen 和插入次数计算
     */
    private final int k;
    /**
     * Bloom filter的结果数据
     */
    private final long[] result;

    public BloomFilter(int expectInsertions) {
        this(expectInsertions, 0.03);
    }

    /**
     * Constructor.
     *
     * @param expectedInsertions insert times
     * @param fpp                false positive probability
     */
    public BloomFilter(int expectedInsertions, double fpp) {
        this.bitLen = optimalNumOfBits(expectedInsertions, fpp);
        this.k = optimalNumOfHashFunctions(expectedInsertions, bitLen);

        // align the bitLen.
        bitLen = ((bitLen + 63) / 64) << 6;
        bitLen = Math.max(bitLen, 1);
        result = new long[checkedCast(bitLen >> 6)];
    }

    private static int checkedCast(long value) {
        int result = (int) value;
        if (result != value) {
            throw new IllegalArgumentException(String.format("Out of range: %s", value));
        }
        return result;
    }

    private static int hash(byte[] key) {
        if (key == null) {
            return 0;
        }
        int h = 1;
        for (byte b : key) {
            h = (h << 5) + h + b;
        }
        return h;
    }

    /**
     * Computes m (total bits of Bloom filter) which is expected to achieve, for the specified
     * expected insertions, the required false positive probability.
     *
     * <p>See http://en.wikipedia.org/wiki/Bloom_filter#Probability_of_false_positives for the
     * formula.
     *
     * @param n expected insertions (must be positive)
     * @param p false positive rate (must be 0 < p < 1)
     */
    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * Computes the optimal k (number of hashes per element inserted in Bloom filter), given the
     * expected insertions and total number of bits in the Bloom filter.
     *
     * <p>See http://en.wikipedia.org/wiki/File:Bloom_filter_fp_probability.svg for the formula.
     *
     * @param n expected insertions (must be positive)
     * @param m total number of bits in Bloom filter (must be positive)
     */
    static int optimalNumOfHashFunctions(long n, long m) {
        // (m / n) * log(2), but avoid truncation due to division!
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * 根据输入的key，生成 bloom filter
     */
    public void generate(byte[] key) {
        // Note：这里是通过一个hash函数生成k个值，理论的误判率？
        int h = hash(key);
        for (int t = 0; t < k; t++) {
            // 防止h为负数，第一次取模后结果为负数
            long idx = (h % bitLen + bitLen) % bitLen;
            result[(int)(idx / 64)] |= (1L << (idx % 64));
            // 偏移量
            int delta = (h >> 17) | (h << 15);
            h += delta;
        }
    }

    public boolean contains(byte[] key) {
        assert result != null;
        int h = hash(key);
        for (int t = 0; t < k; t++) {
            long idx = (h % bitLen + bitLen) % bitLen;
            if ((result[(int)idx / 64] & (1L << (idx % 64))) == 0) {
                return false;
            }
            int delta = (h >> 17) | (h << 15);
            h += delta;
        }
        return true;
    }
}
