package com.xliu.cs.leetcode.greedy;

import com.xliu.cs.generate.ClassNote;

import java.util.Scanner;

/**
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
 * {1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列,
 * {1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
 * 现在给出一个数字序列，允许使用一种转换操作：
 * 选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数之前的位置(只插入一个和)。
 * 现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。
 */
@ClassNote("变成回文序列")
public class SumHuiWen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            System.out.println(getMinSumHW(nums));
        }
    }

    /**
     * 贪心策略，从首尾开始，数字较小的进行转换，相等则首+1，尾-1
     */
    public static int getMinSumHW(int[] nums) {
        int begin = 0, end = nums.length - 1;
        int res = 0;
        while (begin < end) {
            if (nums[begin] < nums[end]) {
                begin ++; res++;
                nums[begin] += nums[begin - 1];
            } else if (nums[begin] > nums[end]){
                end--; res++;
                nums[end] += nums[end + 1];
            } else {
                begin++; end--;
            }
        }
        return res;
    }
}
