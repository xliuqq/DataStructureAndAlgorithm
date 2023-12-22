package org.xliu.cs.algs_ds.algs.math;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

@ClassNote("平方根计算方法")
public class Sqrt {
    /**
     * 牛顿公式计算平方根
     */
    @MethodNote("牛顿公式计算平方根")
    public static int newTonSqrt(int x) {
        if (x <= 0) return 0;
        long r = x;
        while (r * r > x)
            r = (r + x / r) >> 1;
        return (int) r;
    }

    /**
     * 平方根倒数速算法
     */
    @MethodNote("平方根倒数速算法")
    public static int fastSqrt(int num) {
        float xHalf = 0.5F * num;
        int temp = Float.floatToRawIntBits(num);
        temp = 0x5F3759DF - (temp >> 1);
        float newX = Float.intBitsToFloat(temp);
        newX = newX * (1.5F - xHalf * newX * newX);
        return (int) (1 / newX);
    }
}
