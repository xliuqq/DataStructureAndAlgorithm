package org.xliu.cs.algs_ds.algs.math;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.math.Fibonacci;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void performance() {
        int a1 = 0, a2 = 0;
        long begin = System.nanoTime();
        for (int i = 1; i <= 100000; i++) {
            a1 = Fibonacci.getNElem(i);
        }
        long end = System.nanoTime();
        System.out.println("Recursion: " + (end - begin) / 1000000.0 + "ms");
        begin = System.nanoTime();
        for (int i = 1; i <= 100000; i++) {
            a2 = Fibonacci.getNElemMatrix(i);
        }
        end = System.nanoTime();
        System.out.println("Matrix: " + (end - begin) / 1000000.0 + "ms");

        assertEquals(a1, a2);
    }

    @Test
    void matrix() {
        Fibonacci.Matrix m = new Fibonacci.Matrix(1, 1, 1, 0);
        Fibonacci.Matrix m1 = new Fibonacci.Matrix(1, 1, 1, 0);
        m.multi(m1);
        assertEquals(2, m.ma);
        assertEquals(1, m.mb);
        assertEquals(1, m.mc);
        assertEquals(1, m.md);
        m.multi(m1);
        m.multi(m1);
        System.out.println(m.ma + ", " + m.mb + ";" + m.mc + "," + m.md);
    }
}