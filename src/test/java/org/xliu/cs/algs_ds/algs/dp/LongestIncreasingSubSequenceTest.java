package org.xliu.cs.algs_ds.algs.dp;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.dp.LongestIncreasingSubSequence;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubSequenceTest {

    @Test
    public void testGetLISLen() {
        int[] nums = {6, 7, 8, 1, 2, 3, 10, 4, 5};
        assertEquals(5, LongestIncreasingSubSequence.getLISLen(nums));
        assertEquals(5, LongestIncreasingSubSequence.getLISLenBS(nums));
    }

}