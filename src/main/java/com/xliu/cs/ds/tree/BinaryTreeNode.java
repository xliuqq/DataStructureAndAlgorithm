package com.xliu.cs.ds.tree;

import lombok.Data;

@Data
public class BinaryTreeNode<E> {
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;

    private E val;

    public BinaryTreeNode(E v) {
        this.val = v;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }
}
