package org.xliu.cs.algs_ds.algs.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixReverseSortTest {

    @Test
    void search() {
        int[] arr = {1, 3, 4, 2, 5};
        int nums = new PrefixReverseSort(arr).search();
        assertEquals(3, nums);
    }
}