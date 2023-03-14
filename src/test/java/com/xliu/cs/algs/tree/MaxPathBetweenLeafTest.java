package com.xliu.cs.algs.tree;

import com.xliu.cs.ds.tree.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxPathBetweenLeafTest {

    @Test
    void getLen() {
        BinaryTreeNode<Integer> treeNode = new BinaryTreeNode<>(0);
        treeNode.setLeft(new BinaryTreeNode<>(1));
        treeNode.getLeft().setLeft(new BinaryTreeNode<>(3));
        treeNode.getLeft().setRight(new BinaryTreeNode<>(4));
        treeNode.getLeft().getLeft().setRight(new BinaryTreeNode<>(5));
        treeNode.getLeft().getRight().setRight(new BinaryTreeNode<>(6));
        //            0
        //        1
        //      3  4
        //       5  6
        MaxPathBetweenLeaf maxPathBetweenLeaf = new MaxPathBetweenLeaf(treeNode);

        assertEquals(4, maxPathBetweenLeaf.getMaxPathLen());

        assertEquals(3, maxPathBetweenLeaf.getMaxDepth());
    }
}