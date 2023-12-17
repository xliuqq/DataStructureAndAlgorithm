package com.xliu.cs.algs.utils;

import com.xliu.cs.algs.search.BinarySearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void binarySearch() {
        int[] data = {1, 3, 4, 4, 10, 12, 18, 29, 38};
        assertEquals(-1, BinarySearch.search(data, 0));
        assertEquals(4, BinarySearch.search(data, 10));
        assertEquals(-7, BinarySearch.search(data, 13));
    }

    @Test
    void minEqualBS() {
        int[] data = {1, 3, 4, 4, 10, 12, 18, 38, 38};
        assertEquals(-1, BinarySearch.minEqualBS(data, 15));
        assertEquals(2, BinarySearch.minEqualBS(data, 4));
        assertEquals(7, BinarySearch.minEqualBS(data, 38));
    }

    @Test
    void maxEqualBS() {
        int[] data = {1, 3, 4, 4, 10, 12, 18, 38, 38};
        assertEquals(-1, BinarySearch.maxEqualBS(data, 15));
        assertEquals(3, BinarySearch.maxEqualBS(data, 4));
        assertEquals(8, BinarySearch.maxEqualBS(data, 38));
    }

    @Test
    void maxLessBS() {
        int[] data = {1, 3, 4, 4, 10, 12, 18, 38, 38};
        assertEquals(-1, BinarySearch.maxLessBS(data, 0));
        assertEquals(1, BinarySearch.maxLessBS(data, 4));
        assertEquals(6, BinarySearch.maxLessBS(data, 38));
    }

    @Test
    void minGreaterBS() {
        int[] data = {1, 3, 4, 4, 10, 12, 18, 38, 38};
        assertEquals(-1, BinarySearch.minGreaterBS(data, 40));
        assertEquals(4, BinarySearch.minGreaterBS(data, 4));
        assertEquals(0, BinarySearch.minGreaterBS(data, 0));
    }
}