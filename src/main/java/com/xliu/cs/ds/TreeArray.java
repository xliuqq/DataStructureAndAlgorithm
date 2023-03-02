package com.xliu.cs.ds;

/**
 * 树状数组
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
public class TreeArray {

    private int[] data;

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

    public static void main(String[] args) {
        int nums = 10;
        TreeArray treeArray = new TreeArray(nums);
        for (int i = 1; i <= nums; i++) {
            treeArray.add(i, i);
        }
        // 需要开启 -ea ，启动断言
        assert treeArray.sum(1, 5) == 15;
        assert treeArray.sum(1, 10) == 55;
        assert treeArray.sum(3, 6) == 18;
    }
}
