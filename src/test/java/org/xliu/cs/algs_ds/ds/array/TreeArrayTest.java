package org.xliu.cs.algs_ds.ds.array;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.array.TreeArray;

import static org.junit.jupiter.api.Assertions.*;

class TreeArrayTest {

    @Test
    void sum() {
        int nums = 10;
        TreeArray treeArray = new TreeArray(nums);
        for (int i = 1; i <= nums; i++) {
            treeArray.add(i, i);
        }
        // 需要开启 -ea ，启动断言
        assertEquals(15, treeArray.sum(1, 5));
        assertEquals(55, treeArray.sum(1, 10));
        assertEquals(18, treeArray.sum(3, 6));
    }
}