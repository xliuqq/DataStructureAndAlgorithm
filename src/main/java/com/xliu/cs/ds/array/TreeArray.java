package com.xliu.cs.ds.array;

import com.xliu.cs.generate.ClassNote;

/**
 * 树状数组，支持两种操作：
 * - add(n, v): 第 n 个位置加上值 v
 * - sum(n, m): [n, m]区段求和
 *
 * 复杂度未O(log(n))。
 * @formatter:off
 *                 c4
 *            /    |
 *       c2       /|
 *     / |       / |
 *  c1   |    c3   |
 *  |    |    |    |
 * 001  010  011  100
 * @formatter:on
 */
@ClassNote("树状数组")
public class TreeArray {

    private final int[] data;

    public TreeArray(int nums) {
        data = new int[nums];
    }

    public void add(int position, int value) {
        if (position < 1 || position > data.length) {
            throw new IllegalArgumentException("参数非法");
        }
        int max = data.length;

        while (position <= max) {
            data[position - 1] += value;
            // 寻找上一层
            position = position + (position & (-position));
        }
    }

    /**
     * 求sum 和 start间的数据和
     *
     * @param start : 起始位置， include
     * @param end   : 终止位置， include
     * @return sum([start, end])
     */
    public int sum(int start, int end) {
        if (end > data.length || end < start || start < 1) {
            throw new IllegalArgumentException("参数非法");
        }
        if (start == 1) {
            return sum(end);
        }
        return sum(end) - sum(start - 1);
    }

    private int sum(int end) {
        int sum = 0;

        while (end > 0) {
            sum += data[end - 1];
            end = end - (end & (-end));
        }
        return sum;
    }

}
