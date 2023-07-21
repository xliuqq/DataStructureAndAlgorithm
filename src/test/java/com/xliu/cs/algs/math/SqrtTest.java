package com.xliu.cs.algs.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SqrtTest {

    @Test
    void fastSquare() {

        for (int i=0; i<100; i++) {
            int num = (int)(Math.random() * 100000);
            Assertions.assertEquals((int)(Math.sqrt(num)), Sqrt.newTonSqrt(num), "newton num:" + num);
            Assertions.assertTrue(Math.abs(Math.sqrt(num) - Sqrt.newTonSqrt(num)) < 1);
        }
    }
}