package org.xliu.cs.algs_ds.algs.bit;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.bit.BitMapSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BitMapSortTest {

    @Test
    void sort() {
        int[] array = {2, 3, 1, 4, 5, 7, 10};
        int[] clone = array.clone();

        BitMapSort.sort(array, 20);
        assertTrue(array[0] != clone[0]);
        Arrays.sort(clone);
        assertArrayEquals(array, clone);
    }


}