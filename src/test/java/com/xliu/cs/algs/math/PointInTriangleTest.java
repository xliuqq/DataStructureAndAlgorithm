package com.xliu.cs.algs.math;

import com.xliu.cs.ds.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointInTriangleTest {

    @Test
    void isInTriangleViaArea() {
        Point<Integer> a = new Point<>(1, 2);
        Point<Integer> b = new Point<Integer>(3, 4);
        Point<Integer> c = new Point<Integer>(3, 2);

        assertTrue(PointInTriangle.isInTriangle(a, a, b, c));
        assertTrue(PointInTriangle.isInTriangle(b, a, b, c));
        assertTrue(PointInTriangle.isInTriangle(c, a, b, c));
        assertFalse(PointInTriangle.isInTriangle(new Point<>(1, 3), a, b, c));


        assertTrue(PointInTriangle.isInTriangleViaArea(a, a, b, c));
        assertTrue(PointInTriangle.isInTriangleViaArea(b, a, b, c));
        assertTrue(PointInTriangle.isInTriangleViaArea(c, a, b, c));
        assertFalse(PointInTriangle.isInTriangleViaArea(new Point<>(1, 3), a, b, c));

    }
}