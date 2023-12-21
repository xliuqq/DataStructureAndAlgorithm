package com.xliu.cs.algs.math;


import com.xliu.cs.algs.utils.MathUtil;
import com.xliu.cs.ds.Point;
import com.xliu.cs.generate.ClassNote;
import com.xliu.cs.generate.MethodNote;

/**
 * Judge Point in Triangle or not
 */
@ClassNote("点在三角形内")
public class PointInTriangle {

    /**
     * 通过面积判断点是否在三角形内
     */
    @MethodNote("通过面积判断")
    public static boolean isInTriangleViaArea(Point<Integer> p, Point<Integer> ta,
                                              Point<Integer> tb, Point <Integer>tc) {
        double total = getTriangleArea(ta, tb, tc);
        // TODO: 是否需要更精确的表示
        double a = getTriangleArea(p, ta, tb);
        double b = getTriangleArea(p, tb, tc);
        double c = getTriangleArea(p, ta, tc);

        return (a + b + c) <= total;
    }

    /**
     * 通过叉积判断点是否在三角形内；
     * 对于凸多边形（如三角形），如果点D在三角形ABC内，则沿着三角形的边界逆时针走点D一定在边界的左边，
     *       A
     *      /\
     *     /  \
     *    /    \
     *   /   D  \
     *  /________\
     * B       C
     * 即点D在边AB、BC、CA的左边；
     * 判断点P是否在射线RT的左边，可以通过向量RT和RP的叉积的正负性判断，正为左，负为右，0为在射线上；
     */
    @MethodNote("通过叉积判断")
    public static boolean isInTriangle(Point<Integer> p, Point<Integer> ta, Point<Integer> tb, Point<Integer> tc) {
        // 需要确保ta, tb, tc是三角形的逆时针顺序
        if (product(ta, tb, tc) < 0) { // 顺时针，交换tb, tc
            Point<Integer> tmp = tb;
            tb = tc;
            tc = tmp;
        }
        return product(ta, tb, p) >= 0 && product(tb, tc, p) >= 0 && product(tc, ta, p) >= 0;
    }

    private static double getTriangleArea(Point<Integer> p, Point<Integer> q, Point<Integer> r) {
        double a = getEdgeLength(p, q);
        double b = getEdgeLength(p, r);
        double c = getEdgeLength(q, r);

        return MathUtil.triangleArea(a, b, c);
    }
    private static double getEdgeLength(Point<Integer> p, Point<Integer> q) {
        int x = p.x - q.x, y = p.y - q.y;
        return Math.sqrt(x * x + y * y);
    }


    /** 向量ab 叉乘 ac */
    private static double product(Point<Integer> a, Point<Integer> b, Point<Integer> c) {
        // x1 * y2 - x2 * y1
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }


}
