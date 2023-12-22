package org.xliu.cs.algs_ds.ds.tree;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import lombok.Data;

import java.util.Objects;

@Data
@ClassNote("二叉树")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right) && Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, val);
    }
}
