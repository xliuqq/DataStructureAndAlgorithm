package com.xliu.cs.ds.tree;

/**
 * 维护区间信息（要求满足结合律）。与树状数组相比，它可以实现 Olg(n) 的区间修改，还可以同时支持多种操作（加、乘)，更具通用性。
 * 平衡二叉树
 */
public class SegmentTree {

    private static class SegmentNode extends BinaryTreeNode<Integer> {
        private final int start, end, interval;

        public SegmentNode(int s, int e) {
            // sum is the val
            super(0);
            start = s;
            end = e;
            interval = end - start;
        }
    }

    private final int[] nums;
    /**
     * 可以参考堆的实现，使用数组结构实现存储
     */
    private final SegmentNode root;
    private final int n;

    public SegmentTree(int[] numbers) {
        n = numbers.length;
        nums = numbers;
        root = buildTree(0, n - 1);
        root.setVal(root.getLeft().getVal() + root.getRight().getVal());
    }

    private SegmentNode buildTree(int start, int end) {
        if (start == end) {
            SegmentNode newNode = new SegmentNode(start, start);
            newNode.setVal(nums[start]);
            return newNode;
        } else {
            int mid = start + (end - start) / 2;
            SegmentNode newNode = new SegmentNode(start, end);
            newNode.setLeft(buildTree(start, mid));
            newNode.setRight(buildTree(mid + 1, end));
            newNode.setVal(newNode.getLeft().getVal() + newNode.getRight().getVal());
            return newNode;
        }
    }

    /**
     * set the i'th element value
     */
    public void update(int i, int val) {
        // if here use val, then node.sum = node.left.sum + node.right.sum
        update(root, i, val - nums[i]);
        nums[i] = val;
    }

    private void update(SegmentNode node, int pos, int inc) {
        while (node.start != node.end) {
            int mid = node.start + node.interval / 2;
            node.setVal(node.getVal() + inc);
            if (pos <= mid) {
                node = (SegmentNode) node.getLeft();
            } else {
                node = (SegmentNode) node.getRight();
            }
        }
        // start == end, the i'th element node
        node.setVal(node.getVal() + inc);
    }

    /**
     * sum from index i to j, inclusive
     */
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private int sumRange(SegmentNode node, int start, int end) {
        if (node.start == start && node.end == end) {
            return node.getVal();
        } else {
            int mid = node.start + node.interval / 2;
            if (mid < start) {
                return sumRange((SegmentNode) node.getRight(), start, end);
            } else if (mid >= end) {
                return sumRange((SegmentNode) node.getLeft(), start, end);
            } else {
                return sumRange((SegmentNode) node.getLeft(), start, mid) +
                        sumRange((SegmentNode) node.getRight(), mid + 1, end);
            }
        }
    }
}
