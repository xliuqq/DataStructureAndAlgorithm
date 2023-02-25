package com.xliu.lifelearning.cs.ds.tree;

import java.util.Arrays;

/**
 * 并查集
 */
public class UnionSet {
    /**
     * 元素，下标为元素索引，值分为两种情况：
     * 1）值 >= 0，表示其直接父节点索引；
     * 2）值 < 0，表示该集合对应的树的节点的个数。
     * 初始值为-1，表示每个节点都是个单独的集合（树且层数为1）
     */
    private int[] ids;
    /**
     * 节点个数
     */
    private int size;

    public UnionSet(int size) {
        this.size = size;
        ids = new int[this.size];
        Arrays.fill(ids, -1); // use -1 to represent its parent is itself
    }

    public void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);

        if (ids[pi] > ids[pj]) {
            // i's set size < j's set size
            ids[pj] += ids[pi];
            ids[pi] = pj;
        } else {
            // i's set size > j's set size
            ids[pi] += ids[pj];
            ids[pj] = pi;
        }

    }

    public boolean isSameSet(int i, int j) {
        return find(i) == find(j);
    }

    private int find(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("input parameter should not be negative.");
        }

        // 路径压缩，全部直接成为根节点的直接子节点，递归可改成双重循环
       if (ids[index] >= 0) {
            ids[index] = find(ids[index]);
        } else {
           // index is the parent
           return index;
       }

        return ids[index];
    }

    /**
     * 独立的集合的个数
     */
    public int countSet() {
        int count = 0;
        for (int id : ids) {
            if (id < 0) {
                count++;
            }
        }
        return count;
    }

    public int getSetSize(int i) {
        return -ids[find(i)];
    }
}
