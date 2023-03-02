package com.xliu.cs.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HeapTest {

    @Test
    void heapUse() {
        Heap<Integer> heap = new Heap<>(1);

        List<Integer> actual = new ArrayList<>();
        for (int i = 1000; i > 0; i--) {
            int value = (int) (Math.random() * 10000);
            heap.add(value);

            actual.add(value);
        }
        actual.sort(Integer::compareTo);

        Integer value;
        List<Integer> orders = new ArrayList<>(heap.getSize());
        while ((value = heap.poll()) != null) {
            orders.add(value);
        }
        Assertions.assertEquals(actual, orders);
    }

    @Test
    public void test() {
        for (int i = -10; i < 10; i++) {
            int f = (i - 2) / 2;
            int s = i / 2;
            System.out.println(i + ": <= " + f + ", <" + s);
        }
    }
}