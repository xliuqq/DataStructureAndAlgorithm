package org.xliu.cs.algs_ds.algs.dp;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * Your codes' algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 * Hint: Use Binary Search
 */
@ClassNote("最长增长子序列")
public class LongestIncreasingSubSequence {
    /**
     * LIS(i+1) = max {1, LIS[k] + 1}, array[i+1] > k, for any k <= i
     * O(N * N + N) time complexity, O(N) space
     */
    public static int getLISLen(int[] nums) {
        int len = nums.length;
        int[] lIS = new int[len];

        int max = 0;
        for (int i=0; i<len; i++) {
            lIS[i] = 1;
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    lIS[i] = Math.max(lIS[i], lIS[j] + 1);
                }
            }
            max = Math.max(lIS[i], max);
        }

        return max;
    }

    /**
     * Use binary search to find the LIS before nums[i]
     * tail[k]保存长度为k的最长子串的最大值， tail[k-1] <= tail[k]
     * O(N * lg(N)) time complexity
     */
    public static int getLISLenBS(int[] nums) {
        int[] tails = new int[nums.length];
        int sizeOfTail = 0;
        for (int currentNum  : nums) {
            // find the i, meets tail[i] < currentNum <= tail[j]
            int i = 0, j = sizeOfTail; // start from 0 not 1, sure tail[i] < currentNum <= tail[j]
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < currentNum)
                    i = m + 1;
                else
                    j = m;
            }
            // 更新 tail[i] 为 长度为 i的串中的，最后一位的最小值
            tails[i] = currentNum;
            // 当前记录长度为k的字串的最大值 < currentNum
            if (i == sizeOfTail) ++sizeOfTail;
        }
        return sizeOfTail;
    }

}
