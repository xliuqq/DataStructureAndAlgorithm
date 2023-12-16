package com.xliu.cs.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 牛牛的作业薄上有一个长度为 n 的排列 A，这个排列包含了从1到n的n个数，但是因为一些原因，其中有一些位置（不超过 10 个）看不清了，
 * 但是牛牛记得这个数列顺序对的数量是 k，顺序对是指满足 i < j 且 A[i] < A[j] 的对数，请帮助牛牛计算出，符合这个要求的合法排列的数目。
 * <p>
 * 输入描述:
 * 每个输入包含一个测试用例。每个测试用例的第一行包含两个整数 n 和 k（1 <= n <= 100, 0 <= k <= 1000000000），接下来的 1 行，
 * 包含 n 个数字表示排列 A，其中等于0的项表示看不清的位置（不超过 10 个）。
 * <p>
 * 输出描述:
 * 输出一行表示合法的排列数目。
 * <p>
 * 输入例子:
 * 5 5
 * 4 0 0 2 0
 * 输出例子:
 * 2
 */
public class ArrayRestore {
    public static int calculate(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        LinkedList<Integer> zeroPos = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroPos.add(i);
            } else {
                set.add(nums[i]);
            }
        }
        int[] unknown = new int[zeroPos.size()];
        int cnt = 0;
        // not known numbers
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                unknown[cnt++] = i;
            }
        }

        // known order tuples
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != 0 && nums[j] != 0 && nums[i] < nums[j])
                    cnt++;
            }
        }

        int left = k - cnt;

        return get(nums, zeroPos, unknown, 0, left, 0);

    }

    private static int get(int[] nums, List<Integer> fill, int[] unknown, int pos, int left, int curr) {
        if (curr == left && pos == unknown.length) return 1;
        if (curr > left || pos == unknown.length) return 0;

        int fillPos, tmp;
        int result = 0;

        fillPos = fill.get(pos);
        for (int idx = pos; idx < unknown.length; idx++) { //choose unknown idx

            tmp = unknown[idx];
            unknown[idx] = unknown[pos];
            unknown[pos] = tmp;

            nums[fillPos] = unknown[pos];

            tmp = curr;
            for (int i = 0; i < fillPos; i++) {
                if (nums[i] < nums[fillPos]) tmp++;
            }

            for (int i = fillPos + 1; i < nums.length; i++) {
                if (nums[fillPos] < nums[i]) tmp++;
            }
            // choose idx position
            result += get(nums, fill, unknown, pos + 1, left, tmp);

            // not choose idx position
            nums[fillPos] = 0;
            tmp = unknown[pos];
            unknown[pos] = unknown[idx];
            unknown[idx] = tmp;

        }

        return result;
    }

}
