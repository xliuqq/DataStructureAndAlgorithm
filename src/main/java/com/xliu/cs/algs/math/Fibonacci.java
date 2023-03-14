package com.xliu.cs.algs.math;

/**
 * Fibonacci Recursion
 * F(n) = F(n-1) + F(n-2)
 * (F(n), F(n-1)) =  A * [F(n-1); F(n-2)] = A ^ (n-2) * [ F(2); F(1) ]
 * A = [1, 1; 1, 0]
 * <p>
 * A ^ n 可以用n的二进制表示，通过A的2^i次方幂相乘形式
 * A ^ 9 = (A ^ 8) * A, 9 = 1001
 */
public class Fibonacci {
    public static int getNElem(int n) {
        if (n <= 2) return 1;

        int f1 = 1, f2 = 1, res;
        for (int i=3; i<=n; i++) {
            res = f1 + f2;
            f1 = f2;
            f2 = res;
        }
        return f2;
    }

    /** Matrix represents as [ma, mb; mc, md] */
    static class Matrix {
        int ma, mb, mc, md;

        public Matrix(int a, int b, int c, int d) {
            ma = a; mb = b; mc = c; md = d;
        }

        public void multi(Matrix m) {
            int a = ma * m.ma + mb * m.mc;
            int b = ma * m.mb + mb * m.md;
            int c = mc * m.ma + md * m.mc;
            int d = mc * m.mb + md * m.md;
            ma = a; mb = b; mc = c; md = d;
        }

    }

    public static int getNElemMatrix(int n) {
        if (n  <= 2) return 1;
        n -= 2;
        Matrix m = new Matrix(1, 1, 1, 0);
        Matrix res = new Matrix(1, 0, 0, 1);
        while (n != 0) {
            // keep m ^ (2 ^ k)
            if ((n & 1) == 1) {
                res.multi(m);
            }
            // m ^ (2 ^ k)
            m.multi(m);
            n >>>= 1;
        }
        // F(2) = F(1) = 1
        return res.ma + res.mb;
    }
}
