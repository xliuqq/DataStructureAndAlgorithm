package com.xliu.cs.leetcode.recusive;

import com.xliu.cs.leetcode.ArrayRestore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRestoreTest {

    @Test
    void calculate() {
        int[] nums = {4, 0, 0, 2, 0};

        assertEquals(2, ArrayRestore.calculate(nums, 5));
    }
}