package com.xliu.cs.leetcode.divideconquer;

import com.xliu.cs.generate.ClassNote;
import com.xliu.cs.generate.MethodNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of size n, find the majority element.
 */
@ClassNote("数组里的大多数元素")
public class MajorityElement {
    /**
     * Given an array of size n, find the majority element. The majority element is the element that appears
     * more than ⌊ n/2 ⌋ times.
     * major表示该数，遇到相等cnt++，遇到不等cnt--(等价于从数组中消除两个不同的数，原来超过n/2的数还超过n/2)
     */
    @MethodNote("超过1/2的元素")
    public static int halfElement(int[] nums) {
        int len = nums.length;
        int cnt = 0;
        int major = 0;
        for (int num : nums) {
            if (major == num) {
                cnt++;
            } else if (cnt == 0) {
                major = num;
                cnt = 1;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int val : nums) {
            if (val == major) cnt++;
        }
        if (cnt > len / 2) return major;

        return -1;
    }

    /**
     * Given an array of size n, find the majority element. The majority element is the element that appears
     * more than ⌊ n/3 ⌋ times.
     * a, b 表示可能的最多元素，消除元素时，a和b的计数器同时减1(消除三个不同的数，超过n/3的元素还是超过n/3)
     */
    @MethodNote("超过1/3的元素")
    public static List<Integer> oneThirdElement(int[] nums) {
        int a = 0, b = 0, aCnt = 0, bCnt = 0;

        for (int val : nums) {
            // 先判断元素相等，然后判断元素个数
            if (val == a) {
                aCnt++;
            } else if (val == b) {
                bCnt++;
            } else if (aCnt == 0) {
                a = val; aCnt++;
            } else if (bCnt == 0) {
                b = val; bCnt++;
            } else {
                aCnt--; bCnt--;
            }
        }
        aCnt = bCnt = 0;
        for (int val : nums) {
            if (val == a) aCnt++;
            else if (val == b) bCnt++;
        }
        int threshold = nums.length / 3;
        List<Integer> res = new ArrayList<>();
        if (aCnt > threshold) {
            res.add(a);
        }
        if (bCnt > threshold) {
            res.add(b);
        }
        return res;
    }

}
