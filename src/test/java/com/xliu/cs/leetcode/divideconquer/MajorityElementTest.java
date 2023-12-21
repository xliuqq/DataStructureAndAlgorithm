package com.xliu.cs.leetcode.divideconquer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MajorityElementTest {

    @Test
    void halfElement() {
        int[] elems = {1, 1, 2, 3, 1};
        int res = MajorityElement.halfElement(elems);

        assertEquals(1, res);
    }

    @Test
    void oneThirdElement() {
        int[] elems = {1, 1, 2, 3, 1, 2, 2};
        List<Integer> res = MajorityElement.oneThirdElement(elems);


        assertEquals(new HashSet<>(Arrays.asList(1, 2)), new HashSet<>(res));
    }
}