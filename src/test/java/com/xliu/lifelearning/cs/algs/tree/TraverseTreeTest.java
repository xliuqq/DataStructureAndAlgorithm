package com.xliu.lifelearning.cs.algs.tree;

import com.xliu.lifelearning.cs.ds.tree.BinaryTreeNode;
import com.xliu.lifelearning.cs.utils.TreeTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TraverseTreeTest {

    /*
     *          0
     *      1           2
     *   3    4      5     6
     * 7  8  9 10  11 12  13
     */
    @Test
    void preOrderRecursive() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.preOrderRecursive(root, orders);

        Assertions.assertEquals("[0, 1, 3, 7, 8, 4, 9, 10, 2, 5, 11, 12, 6, 13]", orders.toString());

    }

    @Test
    void preOrder() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.preOrder(root, orders);

        Assertions.assertEquals("[0, 1, 3, 7, 8, 4, 9, 10, 2, 5, 11, 12, 6, 13]", orders.toString());

    }

    @Test
    void inOrderRecursive() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.inOrderRecursive(root, orders);

        Assertions.assertEquals("[7, 3, 8, 1, 9, 4, 10, 0, 11, 5, 12, 2, 13, 6]", orders.toString());

    }

    @Test
    void inOrder() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.inOrder(root, orders);

        Assertions.assertEquals("[7, 3, 8, 1, 9, 4, 10, 0, 11, 5, 12, 2, 13, 6]", orders.toString());

    }

    @Test
    void postOrderRecursive() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.postOrderRecursive(root, orders);

        Assertions.assertEquals("[7, 8, 3, 9, 10, 4, 1, 11, 12, 5, 13, 6, 2, 0]", orders.toString());

    }

    @Test
    void postOrder() {
       // TODO(lzq) add test
    }

    @Test
    void levelOrder() {
        BinaryTreeNode<Integer> root = TreeTestUtils.buildBinaryTree(14);

        List<Integer> orders = new ArrayList<>();
        TraverseTree.levelOrder(root, orders);

        Assertions.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]", orders.toString());
    }
}