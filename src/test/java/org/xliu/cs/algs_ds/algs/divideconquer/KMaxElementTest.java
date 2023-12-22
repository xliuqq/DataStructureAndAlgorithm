package org.xliu.cs.algs_ds.algs.divideconquer;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.divideconquer.KMaxElement;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class KMaxElementTest {

    public static List<Integer> getKMaxWithHeap(List<Integer> nums, int k) {
        // Min Heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);

        int size;
        for (Integer value : nums) {
            size = heap.size();
            if (size < k) {
                heap.add(value);
            } else if (heap.peek() < value) {
                heap.poll();
                heap.add(value);
            }
        }

        List<Integer> res = new ArrayList<>(k);
        res.addAll(heap);
        return res;
    }

    @Test
    void getKMax() {
        long timeA = 0, timeB = 0;
        for (int times = 0; times < 6; times++) {
            Integer[] array = new Integer[10000000];
            int len = array.length;
            for (int i = 0; i < len; i++) {
                array[i] = (int) (Math.random() * len);
            }

            List<Integer> elems = Arrays.asList(array);
            long begin = System.nanoTime();
            List<Integer> res = KMaxElement.getKMax(elems, len / 10);
            long end = System.nanoTime();

            timeA += end - begin;

            begin = System.nanoTime();
            List<Integer> res2 = getKMaxWithHeap(elems, len / 10);
            end = System.nanoTime();

            timeB += end - begin;

            Collections.sort(res);
            Collections.sort(res2);
            assertEquals(res, res2);
        }

        System.out.println("DivideConquer " + timeA / 20000000.0 + "ms");


        System.out.println("MinHeap " + timeB / 20000000.0 + "ms");
    }
}