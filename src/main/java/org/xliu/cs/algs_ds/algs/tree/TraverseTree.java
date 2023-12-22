package org.xliu.cs.algs_ds.algs.tree;

import org.xliu.cs.algs_ds.ds.tree.BinaryTreeNode;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树的遍历，包括前序、中序、后续遍历。
 */
@ClassNote("二叉树遍历")
public class TraverseTree {

    /**
     * 层次遍历
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    @MethodNote("层次遍历")
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
    @MethodNote("前序遍历（非递归）")
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
    @MethodNote("前序遍历（递归）")
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

    private static class Frame<E> {
        int pc;
        BinaryTreeNode<E> node;
        List<E> store;

        public Frame(BinaryTreeNode<E> node, List<E> store) {
            this.pc = 0;
            this.node = node;
            this.store = store;
        }
    }

    /**
     * 中序遍历，左 -> 根 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    @MethodNote("中序遍历（非递归）")
    public static <E> void inOrder(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }
        Deque<Frame<E>> stack = new LinkedList<>();
        stack.add(new Frame<>(root, store));

        while (!stack.isEmpty()) {
            Frame<E> current = stack.getLast();

            switch (current.pc) {
                case 0:
                    if (current.node == null) {
                        stack.removeLast();
                    }
                    break;
                case 1:
                    if (current.node.getLeft() != null) {
                        stack.add(new Frame<>(current.node.getLeft(), store));
                    }
                    break;
                case 2:
                    store.add(current.node.getVal());
                    break;
                case 3:
                    if (current.node.getRight() != null) {
                        stack.add(new Frame<>(current.node.getRight(), store));
                    }
                    break;
                case 4:
                    stack.removeLast();
                    break;
            }
            current.pc += 1;
        }
    }

    /**
     * 中序遍历，左 -> 根 -> 右
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    @MethodNote("中序遍历（递归）")
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
    @MethodNote("后序遍历（非递归）")
    public static <E> void postOrder(BinaryTreeNode<E> root, List<E> store) {
        if (root == null) {
            return;
        }

        // 保存栈帧，当前栈帧只需要 node
        Deque<Frame<E>> stack = new LinkedList<>();

        stack.add(new Frame<>(root, store));

        while (!stack.isEmpty()) {
            Frame<E> current = stack.getLast();

            switch (current.pc) {
                case 0:
                    if (current.node == null) {
                        stack.removeLast();
                    }
                    break;
                case 1:
                    if (current.node.getLeft() != null) {
                        stack.add(new Frame<>(current.node.getLeft(), store));
                    }
                    break;
                case 2:
                    if (current.node.getRight() != null) {
                        stack.add(new Frame<>(current.node.getRight(), store));
                    }
                    break;
                case 3:
                    store.add(current.node.getVal());
                    break;
                case 4:
                    stack.removeLast();
                    break;
            }
            current.pc += 1;
        }
    }


    /**
     * 后序遍历，左 -> 右 -> 根
     *
     * @param root  根节点
     * @param store 存储遍历的顺序
     */
    @MethodNote("后序遍历（递归）")
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