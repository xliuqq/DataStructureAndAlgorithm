package org.xliu.cs.algs_ds.algs.bit;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.bit.CountBits;

import static org.junit.jupiter.api.Assertions.*;

class CountBitsTest {

    @Test
    void countOneBitsNumber() {
        assertEquals(3, CountBits.countOneBitsNumber(7));
        assertEquals(1, CountBits.countOneBitsNumber(8));
        assertEquals(1, CountBits.countOneBitsNumber(1));
        assertEquals(2, CountBits.countOneBitsNumber(10));
    }

    @Test
    void countOneBitsNumberByAnd() {
        for (int i=1; i<100; i++) {
            assertEquals(CountBits.countOneBitsNumber(i), CountBits.countOneBitsNumberByAnd(i));
        }
    }
}