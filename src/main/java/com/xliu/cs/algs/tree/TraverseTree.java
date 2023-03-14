package com.xliu.cs.algs.tree;

import com.xliu.cs.ds.tree.BinaryTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树的遍历，包括前序、中序、后续遍历。
 */
public class TraverseTree {

    /**
     * 层次遍历
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void levelOrder(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> current = queue.poll();

            // do store
            store.add(current.getVal());

            if (current.hasLeft()) {
                queue.add(current.getLeft());
            }
            if (current.hasRight()) {
                queue.add(current.getRight());
            }
        }

    }

    /**
     * 前序遍历（非递归）， 根 -> 左 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void preOrder(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        Deque<BinaryTreeNode<E>> stack = new LinkedList<>();

        stack.push(root);

        BinaryTreeNode<E> current;

        while (!stack.isEmpty()) {
            current = stack.pop();
            // 先加 根节点
            store.add(current.getVal());

            // 右子树进栈
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
            // 左子树进栈，因此会先遍历左子树
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
    }

    /**
     * 前序遍历， 根 -> 左 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void preOrderRecursive(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        store.add(root.getVal());
        if (root.getLeft() != null) {
            preOrderRecursive(root.getLeft(), store);
        }
        if (root.getRight() != null) {
            preOrderRecursive(root.getRight(), store);
        }
    }

    /**
     * 中序遍历，左 -> 根 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void inOrder(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        // 保存栈帧，当前栈帧只需要 node
        Deque<BinaryTreeNode<E>> stack = new LinkedList<>();

        BinaryTreeNode<E> current  = root;

        while (current != null || !stack.isEmpty()) {
            // 左子树进栈，因此会先遍历左子树 <=> inOrderRecursive(current.left))
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            // 没有栈帧，即current节点的左子树递归完成，存储值
            current = stack.pop();
            store.add(current.getVal());

            // 右节点加入栈帧，进入递归
            current = current.getRight();
        }
    }

    /**
     * 中序遍历，左 -> 根 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void inOrderRecursive(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        if (root.getLeft() != null) {
            inOrderRecursive(root.getLeft(), store);
        }
        store.add(root.getVal());
        if (root.getRight() != null) {
            inOrderRecursive(root.getRight(), store);
        }

    }

    /**
     * 后序遍历，左 -> 右 -> 根
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void postOrder(BinaryTreeNode<E> root, List<E> store) {
        // TODO(lzq) 递归变循环的方法

    }


    /**
     * 后序遍历，左 -> 右 -> 根
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    public static <E> void postOrderRecursive(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        if (root.getLeft() != null) {
            postOrderRecursive(root.getLeft(), store);
        }
        if (root.getRight() != null) {
            postOrderRecursive(root.getRight(), store);
        }
        store.add(root.getVal());
    }
}