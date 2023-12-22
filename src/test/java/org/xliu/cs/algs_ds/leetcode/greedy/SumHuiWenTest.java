package org.xliu.cs.algs_ds.leetcode.greedy;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.leetcode.greedy.SumHuiWen;

import static org.junit.jupiter.api.Assertions.*;

class SumHuiWenTest {

    @Test
    void getMinSumHW() {
        int[] nums = {15, 78, 87, 15};
        assertEquals(1, SumHuiWen.getMinSumHW(nums));
    }

}