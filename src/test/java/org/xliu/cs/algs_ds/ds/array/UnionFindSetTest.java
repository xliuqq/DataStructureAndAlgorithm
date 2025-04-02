package org.xliu.cs.algs_ds.ds.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnionFindSetTest {

    @Test
    public void correctTest() {
        UnionFindSet unionFindSet = new UnionFindSet(10);

        unionFindSet.union(0, 1);
        unionFindSet.union(3, 5);
        unionFindSet.union(2, 1);
        unionFindSet.union(3, 0);

        unionFindSet.union(7, 9);
        unionFindSet.union(6, 8);

        assertTrue(unionFindSet.isSameSet(0, 5));
        assertEquals(4, unionFindSet.countSet());

        assertEquals(unionFindSet.getSetSize(0), 5);
        assertEquals(unionFindSet.getSetSize(7), 2);
        assertEquals(unionFindSet.getSetSize(6), 2);
        assertEquals(unionFindSet.getSetSize(4), 1);
    }
}