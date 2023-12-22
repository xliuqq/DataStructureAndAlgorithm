package org.xliu.cs.algs_ds.leetcode.bit;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

/**
 * Given an array of integers, every element appears A times except for B times. Find that B times integer.
 */
@ClassNote("出现次数不一样的数")
public class SingleNumber {

    /**
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements
     * appear exactly twice. Find the two elements that appear only once.
     * For example:
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     */
    @MethodNote("只有两个元素出现一次，其它都出现两次")
    public static int[] getTwoSingleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        //  a ^ b == xor, xor中为1的位置表明a和b在该位置的值是不一样的，根据这个拆分原数组，
        // 得到两个数组，每个数组只有一个元素出现一次，其它元素出现两次
        int a = 0, b = 0;
        int res = getFirstOneDigit(xor); // int res = xor & (-xor)
        for (int num : nums) {
            if ( (num & res) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    /** Get the first digits which value is 1 */
    private static int getFirstOneDigit(int num) {
        int i = 1;
        while (true) {
            if ( (i & num) != 0) {
                break;
            } else {
                i <<= 1;
            }
        }
        return i;
    }

    /** Given an array of integers, every element appears twice except for one. Find that single one */
    @MethodNote("只有一个元素出现一次，其它都出现两次")
    public static int getSingleNumberInTwice(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    /** Given an array of integers, every element appears three times except for one. Find that single one
     * General Solution:
     * 1) use 2 bit to represent 3 status
     * current    incoming     next
     *   a b         c         a b
     *   0 0         0         0 0   ( incoming is 0, not change a, b)
     *   0 1         0         0 1
     *   1 0         0         1 0
     *   0 0         1         0 1   ( incoming is 1, change to next status)
     *   0 1         1         1 0
     *   1 0         1         0 0   ( three times, to initial status)
     *
     *   a is 1 <==> (a & ~b & ~c) | (~a & b & c)
     *   b is 1 <==> (~a & b & ~c) | (~a & ~b & c)
     *
     *   Three 0 or Three 1 is 0, the left maybe one/two 0 or 1
     *   one / two 0 --> (0, 0) --> 0
     *   one 1       --> (0, 1) --> 1
     *   two 1       --> (1, 0) --> 1
     *   So, the final result is a | b
     * 2) O(32n), for each bit, count nums[i] value in its position, % 3
     */
    @MethodNote("只有一个元素出现一次，其它都出现三次")
    public static int getSingleNumberInThree(int[] nums) {
        int a = 0, b = 0, ta;
        for (int i=0, len=nums.length; i<len; i++) {
            ta = (a & ~b & ~nums[i]) | (~a & b & nums[i]);
            b = (~a & b & ~nums[i]) | (~a & ~b & nums[i]);
            a = ta;
        }
        return a | b;
    }

}
