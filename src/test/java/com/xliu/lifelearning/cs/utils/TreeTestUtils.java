package com.xliu.lifelearning.cs.utils;

import com.xliu.lifelearning.cs.ds.tree.BinaryTreeNode;

public class TreeTestUtils {

    /**
     * 构建二叉树，n = 13 时的示例如下
     *            0
     *        1         2
     *     3   4      5    6
     *   7  8  9 10  11 12 13
     */
    @SuppressWarnings("unchecked")
    public static BinaryTreeNode<Integer> buildBinaryTree(int n) {
        BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[n];

        for (int i=0; i<n; i++) nodes[i] = new BinaryTreeNode<>(i);

        for (int i=0; i<n; i++) {
            int val = 2 * i + 1;
            if (val < n) nodes[i].setLeft(nodes[val]);
            if (val + 1 < n) nodes[i].setRight(nodes[val + 1]);
        }

        return nodes[0];
    }
}
