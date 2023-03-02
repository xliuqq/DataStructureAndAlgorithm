package com.xliu.cs.ds.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionSetTest {

    @Test
    public void correctTest() {
        UnionSet unionSet = new UnionSet(10);

        unionSet.union(0, 1);
        unionSet.union(3, 5);
        unionSet.union(2, 1);
        unionSet.union(3, 0);


        unionSet.union(7, 9);
        unionSet.union(6, 8);


        assertTrue(unionSet.isSameSet(0, 5));
        assertEquals(4, unionSet.countSet());

        assertEquals(unionSet.getSetSize(0), 5);
        assertEquals(unionSet.getSetSize(7), 2);
        assertEquals(unionSet.getSetSize(6), 2);
        assertEquals(unionSet.getSetSize(4), 1);
    }
}