package org.xliu.cs.algs_ds.ds.linkedlist;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.linkedlist.SingleLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    @Test
    void addLast() {
        SingleLinkedList<Integer> first = new SingleLinkedList<>();
        for (int i=0; i<10; i++) {
            first.addLast(i);
        }

        assertEquals(10, first.size());

        for (int i=0; i<10; i++) {
            assertEquals(i, first.removeFirst());
        }
    }

    @Test
    void addFirst() {
        SingleLinkedList<Integer> first = new SingleLinkedList<>();
        for (int i=0; i<10; i++) {
            first.addFirst(i);
        }

        assertEquals(10, first.size());

        for (int i=9; i>=0; i--) {
            assertEquals(i, first.removeFirst());
        }
    }
}