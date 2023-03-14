package com.xliu.cs.algs.tree;

import com.xliu.cs.ds.tree.BinaryTreeNode;

/**
 * 树中两个根节点的最长路径(长度定义为路径上的边的个数)，动态规划 / 递归算法
 * F(root) = Max(F(root.left), F(root.right), Depth(root.left) + Depth(root.right))
 * let S(x) = Depth(x.left) + Depth(x.right)
 * F(root) = Max(S(x)) for all x in root tree
 */
public class MaxPathBetweenLeaf{
    private int currentMax = 0;

    private int maxDepth = 0;

    public MaxPathBetweenLeaf(BinaryTreeNode<?> root) {
        maxDepth = getDepth(root);
    }

    public int getMaxPathLen() {
        return currentMax;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    private <T> int getDepth(BinaryTreeNode<T> root) {
        if (root == null) return 0;

        int leftDepth = 0, rightDepth = 0;

        // root left max depth
        if (root.getLeft() != null) {
            leftDepth = getDepth(root.getLeft()) + 1;
        }

        // root right max depth
        if (root.getRight() != null) {
            rightDepth = getDepth(root.getRight()) + 1;
        }

        // left-root-right left max len = left + right
        int leftRight = leftDepth + rightDepth;

        // the max must be one of the leftRight of some node
        if (currentMax < leftRight) {
            currentMax = leftRight;
        }

        return Math.max(leftDepth, rightDepth);
    }
}
