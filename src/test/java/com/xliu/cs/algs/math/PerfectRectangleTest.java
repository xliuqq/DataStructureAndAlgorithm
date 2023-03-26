package com.xliu.cs.algs.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfectRectangleTest {

    @Test
    void isRectangleCover() {
        int[][] res = new int[][]{
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        };
//        int[][]res =  {
//                {1,1,2,3},
//        {1,3,2,4},
//            {3,1,4,2},
//                {3,2,4,4}
//        };
       assertTrue(PerfectRectangle.isRectangleCover(res));
    }
}