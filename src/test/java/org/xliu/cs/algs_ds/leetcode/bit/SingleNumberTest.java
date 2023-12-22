package org.xliu.cs.algs_ds.leetcode.bit;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.leetcode.bit.SingleNumber;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleNumberTest {

    @Test
    void getTwoSingleNumber() {
        int[] nums = {43772400, 43772400, -145417756, 744132272};
        int[] res = SingleNumber.getTwoSingleNumber(nums);
        assertArrayEquals(new int[]{744132272, -145417756}, res);
    }

    @Test
    void getSingleNumberInTwice() {
        int[] nums = {43772400, 43772400, -145417756};
        int res = SingleNumber.getSingleNumberInTwice(nums);
        assertEquals(-145417756, res);
    }

    @Test
    void getSingleNumberInThree() {
        int[] nums = {43772400, 43772400, 43772400, -145417756};
        int res = SingleNumber.getSingleNumberInThree(nums);
        assertEquals(-145417756, res);
    }
}