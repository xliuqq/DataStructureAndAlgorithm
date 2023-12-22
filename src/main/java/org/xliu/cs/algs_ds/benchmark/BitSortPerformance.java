package org.xliu.cs.algs_ds.benchmark;

import org.xliu.cs.algs_ds.algs.bit.BitMapSort;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.Arrays;
import java.util.HashSet;


@ClassNote("位图排序跟系统排序性能")
public class BitSortPerformance {

    private static int[] randomUniqueIntArray(int size, int maxNumber) {
        int[] data = new int[size];
        HashSet<Integer> set = new HashSet<>(size);

        int cnt = 0;
        while (cnt < size) {
            int value = (int) (Math.random() * maxNumber);
            if (!set.contains(value)) {
                set.add(value);
                data[cnt] = value;
                cnt++;
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int maximum = 10000000;
        // 100 万个元素排序
        int[] array = randomUniqueIntArray(1000000, maximum);
        int[] copy = array.clone();

        long timeBegin = System.nanoTime();
        BitMapSort.sort(array, maximum);
        long timeEnd = System.nanoTime();

        System.out.println("BitMap Sort Time Cost Seconds: " + (timeEnd - timeBegin) / 1000000000.0);

        timeBegin = System.nanoTime();
        Arrays.sort(copy);
        timeEnd = System.nanoTime();

        System.out.println("System Sort Time Cost Seconds:" + (timeEnd - timeBegin) / 1000000000.0);

        System.out.println("Two Results Equals : " + Arrays.equals(array, copy));
    }
}
