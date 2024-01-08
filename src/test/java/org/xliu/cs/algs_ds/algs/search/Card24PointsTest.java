package org.xliu.cs.algs_ds.algs.search;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class Card24PointsTest {

    @Test
    void testPerformance() {
        Card24Points c24p = new Card24Points(new int[]{4, 1, 2, 5, 3});
        val result = c24p.getResult();
        assertNotNull(result);
        System.out.println(result);
    }


    @Test
    void getResult() {
        Card24Points c24p = new Card24Points(new int[]{1, 1, 1, 1});
        assertNull(c24p.getResult());
    }
}