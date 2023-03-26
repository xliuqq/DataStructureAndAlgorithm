package com.xliu.cs.ds;

import java.util.Objects;

/**
 * 二维平面的点的数据结构。
 * 不可变数据结构。
 */
public class Point<T> {
    public final T x;
    public final T y;

    public Point(T a, T b) {
        x = a;
        y = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point<?> point = (Point<?>) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    public int hashCode() {
        return 31 * x.hashCode() + y.hashCode();
    }
}