package org.xliu.cs.algs_ds.ds.tree;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.tree.SegmentTree;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {

    @Test
    void sumRange() {
        SegmentTree bit = new SegmentTree(new int[]{1, 3, 5, 7, 9});
        assertEquals(bit.sumRange(0, 2), 9, "sum should be 9");
        bit.update(1, 2);
        assertEquals(bit.sumRange(1, 4), 23, "sum should be 23");
        bit.update(1, 4);
        assertEquals(bit.sumRange(1, 4), 25, "sum should be 23");

    }
}