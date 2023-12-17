package com.xliu.cs.algs.math;

import java.util.*;

import com.xliu.cs.ds.Point;
import com.xliu.cs.generate.ClassNote;

/**
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.
 * 即 所有的小矩阵正好构成一个大的矩阵，且没有重叠。
 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented
 * as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 * <p/>
 * First calculate each rectangle area sum equals to the whole rectangle area；
 * （充要条件）每个小矩阵上的顶点：
 * 1）是大矩阵的顶点，只能出现一次；
 * 2）是大矩阵边上点，出现0次或两次；
 * 3）是大矩阵内部点，出现0次或4次；
 */
@ClassNote("N个小矩阵构成大矩阵")
public class PerfectRectangle {

    public static boolean isRectangleCover(int[][] rectangles) {
        Map<Point<Integer>, Integer> map = new HashMap<>();
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        long sum = 0;
        for (int[] rec : rectangles) {
            minX = Math.min(minX, rec[0]);
            minY = Math.min(minY, rec[1]);
            maxX = Math.max(maxX, rec[2]);
            maxY = Math.max(maxY, rec[3]);

            sum += ((long)(rec[2] - rec[0])) * (rec[3] - rec[1]);

            insert(map, new Point<>(rec[0], rec[1]));
            insert(map, new Point<>(rec[0], rec[3]));
            insert(map, new Point<>(rec[2], rec[1]));
            insert(map, new Point<>(rec[2], rec[3]));
        }

        if (sum != ((long)(maxX - minX)) * (maxY - minY)) return false;

        Set<Point<Integer>> corners = new HashSet<>();
        corners.add(new Point<>(minX, minY));
        corners.add(new Point<>(maxX, minY));
        corners.add(new Point<>(minX, maxY));
        corners.add(new Point<>(maxX, maxY));
        for (Map.Entry<Point<Integer>, Integer> entry : map.entrySet()) {
            Point<Integer> p = entry.getKey();
            Integer i = entry.getValue();
            if (corners.contains(p)) {
                if (i != 1) return false;
            } else {
                if ( i % 2 != 0) return false;
            }
        }
        return true;
    }

    private static void insert(Map<Point<Integer>, Integer> map, Point<Integer> p) {
        map.merge(p, 1, Integer::sum);
    }


}
