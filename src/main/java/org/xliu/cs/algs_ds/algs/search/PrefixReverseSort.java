package org.xliu.cs.algs_ds.algs.search;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.Arrays;

/**
 * Given an int array length n which value is from 1 to n,
 * find the maximum number of Sort by Prefix Reversal
 */
@ClassNote("1-n的乱序数组前缀反转使其有序的最大次数")
public class PrefixReverseSort {
    /** The maximum sort numbers */
    private int mMaxSwap;
    /** Int Array */
    private int[] mArray;
    /** The swap information array */
    private int[] mSwapArray;
    /** The lenght of array */
    private int mLen;
    /** The search number when find the maximum sort numbers */
    private int mSearchNums;

    public PrefixReverseSort(int[] arr) {
        mArray = arr;
        mLen = arr.length;
        mMaxSwap = upperBound(mLen);
        mSearchNums = 0;
        mSwapArray = new int[mMaxSwap];
    }

    public int search() {
        int[] curSwapArray = new int[mMaxSwap];
        int[] curArray = Arrays.copyOf(mArray, mLen);

        searchMaxSwap(curSwapArray, curArray, 0);

        System.out.println("Search numbers: " + mSearchNums);
        System.out.println("Maximum swap numbers: " + mMaxSwap);

        for (int i=0; i<mMaxSwap; i++) {
            System.out.print(mSwapArray[i] + " ");
        }
        System.out.println();

        return mMaxSwap;
    }


    /** Search the maximum sort numbers from current step */
    private void searchMaxSwap(int[] curInfoArray, int[] curArray, int step) {
        mSearchNums++;

        int estimate = lowerBound(curArray);

        if (isSorted(curArray)) {
            if (step < mMaxSwap) {
                mMaxSwap = step;
                System.arraycopy(curInfoArray, 0, mSwapArray, 0, step);
                System.arraycopy(curArray, 0, mArray, 0, mLen);
            }
            return;
        }

        // swap step times, not sorted
        if (step + estimate >= mMaxSwap) return;

        for (int i=1; i<mLen; i++) {
            reverse(curArray, 0, i);
            curInfoArray[step] = i;
            searchMaxSwap(curInfoArray, curArray, step + 1);
            reverse(curArray, 0, i);
        }
    }

    /** Reverse array from begin to end(include) */
    private void reverse(int[] array, int begin, int end) {
        int t;
        for (int i=begin, j=end; i<j; i++, j--) {
            t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
    }

    /** The upper bound of array length n
     * 将 n 放到最后一位，最多需要2步（先翻转到第一位，再翻转到最后一位）
     * 因此最多 2 * (n-1) 将数组从 1-n 排好
     */
    private int upperBound(int n) {
        return 2 * (n - 1);
    }

    /** The lower bound of array */
    private int lowerBound(int[] array) {
        int n = 0, diff, len = array.length;
        for (int i=1; i<len; i++) {
            diff = array[i] - array[i-1];
            if ( diff != 1 && diff != -1) {
                n++;
            }
        }
        return n;
    }

    /** Whether the array is sorted or not, True means sorted */
    private boolean isSorted(int[] array) {
        int len = array.length;
        for (int i=1; i<len; i++) {
            if (array[i] < array[i-1]) {
                return false;
            }
        }
        return true;
    }
}
