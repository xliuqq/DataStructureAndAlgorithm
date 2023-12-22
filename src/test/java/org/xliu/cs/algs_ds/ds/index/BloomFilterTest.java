package org.xliu.cs.algs_ds.ds.index;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.index.BloomFilter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
    @Test
    public void testBloomFilter() throws IOException {
        String[] keys = {"hello world", "hi", "bloom", "filter", "key", "value", "1", "value"};
        BloomFilter bf = new BloomFilter(100, 0.03);

        for (String key : keys) {
            bf.generate(key.getBytes());
        }

        assertFalse(bf.contains("h".getBytes()));
        assertFalse(bf.contains("he".getBytes()));
        assertTrue(bf.contains("hi".getBytes()));
        assertTrue(bf.contains("hello world".getBytes()));
        assertTrue(bf.contains("bloom".getBytes()));
        assertTrue(bf.contains("key".getBytes()));
    }
}
