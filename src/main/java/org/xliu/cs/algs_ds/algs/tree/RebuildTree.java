package org.xliu.cs.algs_ds.algs.tree;

import org.xliu.cs.algs_ds.ds.tree.BinaryTreeNode;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

/**
 * 由前序遍历和中序遍历，构建子树
 */
@ClassNote("根据遍历序恢复树")
public class RebuildTree {
    @MethodNote("通过前序遍历和中序遍历构造树")
    public static BinaryTreeNode<Integer> buildFromPreInOrder(int[] in, int[] pre) {
        return buildFromPreInOrder(in, 0, pre, 0, in.length);
    }

    private static BinaryTreeNode<Integer> buildFromPreInOrder(int[] in, int inPos, int[] pre, int prePos, int treeLen) {
        int rootVal = pre[prePos];
        BinaryTreeNode<Integer> tmp = new BinaryTreeNode<>(rootVal);

        if (treeLen == 1) return tmp;

        // find in[i] == pre[prePos] == rootVal
        for (int i = inPos, j = inPos + treeLen; i < j; i++) {
            if (in[i] == rootVal) {
                int leftLen = i - inPos, rightLen = treeLen - leftLen - 1;
                if (leftLen > 0) {
                    tmp.setLeft(buildFromPreInOrder(in, inPos, pre, prePos + 1, leftLen));
                }
                if (rightLen > 0) {
                    tmp.setRight(buildFromPreInOrder(in, i + 1, pre, prePos + leftLen + 1, rightLen));
                }
                break;
            }
        }
        return tmp;
    }
}
