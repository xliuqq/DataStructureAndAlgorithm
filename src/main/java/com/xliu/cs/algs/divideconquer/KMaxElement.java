package com.xliu.cs.algs.divideconquer;

import com.xliu.cs.generate.ClassNote;

import java.util.*;

/**
 * Given an array A, return the Top K Max Elements in it.
 */
@ClassNote("数组里的最大的K个数（无序）")
public class KMaxElement {

    public static List<Integer> getKMax(List<Integer> nums, int k) {
        if (nums.size() <= k) return nums;

        List<Integer> lEq = new ArrayList<>();
        List<Integer> gEq = new ArrayList<>();

        int pos = (int) (Math.random() * nums.size());
        Integer random = nums.get(pos); // swap 0 and pos
        nums.set(pos, nums.get(0));
        nums.set(0, random);

        Integer value;
        for (int i = 1, len = nums.size(); i < len; i++) {
            value = nums.get(i);
            if (random > value) {
                lEq.add(value);
            } else {
                gEq.add(value);
            }
        }

        // if the gEq has more than k elements, find the top k in the gEq.
        if (gEq.size() >= k) {
            return getKMax(gEq, k);
        } else {
            // never forgot the random element.
            lEq.add(random);

            gEq.addAll(getKMax(lEq, k - gEq.size()));
            return gEq;
        }
    }


}
