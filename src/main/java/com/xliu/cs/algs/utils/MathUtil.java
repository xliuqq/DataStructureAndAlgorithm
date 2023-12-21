package com.xliu.cs.algs.utils;


import com.xliu.cs.generate.IgnoreNote;

/**
 * Math Util Functions
 */
@IgnoreNote
public class MathUtil {
    /** Use % to get the gcd value, mod costs much*/
    public static int gcd(int x, int y) {
        int mod = x % y;
        return mod == 0 ? y : gcd(y, mod);
    }

    /**
     * 计算三角形的面积。
     * use helen formula to calculate the area of triangle。
     */
    public static double triangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt((p - a) * (p - b) * (p - c) * p);
    }
}
