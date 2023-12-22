package org.xliu.cs.algs_ds.algs.tree;

import org.xliu.cs.algs_ds.ds.tree.BinaryTreeNode;
import org.xliu.cs.algs_ds.utils.TreeTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RebuildTreeTest {

    @Test
    void buildFromPreInOrder() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(15);
        List<Integer> preOrder = new ArrayList<>();
        TraverseTree.preOrder(root, preOrder);
        List<Integer> inOrder = new ArrayList<>();
        TraverseTree.inOrder(root, inOrder);
        int[] in = new int[inOrder.size()];
        int[] pre = new int[preOrder.size()];
        for (int i=0; i<in.length; i++) {
            in[i] = inOrder.get(i);
            pre[i] = preOrder.get(i);
        }
        BinaryTreeNode<Integer> reTree = RebuildTree.buildFromPreInOrder(in, pre);

        Assertions.assertEquals(root, reTree);
    }
}