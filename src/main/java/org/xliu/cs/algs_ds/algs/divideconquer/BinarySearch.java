package org.xliu.cs.algs_ds.algs.divideconquer;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

@ClassNote("二分搜索及其变种")
public class BinarySearch {

    /** 返回等于value的下标，如果不存在，则返回插入该值的（下标+1）的负数*/
    @MethodNote("搜索等于value的下标，如果不存在，则返回插入该值的（下标+1）的负数")
    public static int search(int[] data, int value) {
        int low = 0;
        int high = data.length - 1;
        // [low, high]
        while (low <= high) { // in case of 6, 7, next begin = end is 7, equal
            int mid = (low + high) / 2;
            int midVal = data[mid];
            if (midVal < value)
                low = mid + 1;
            else if (midVal > value)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1); // not found
    }

    /** 搜索等于 value 值的元素中最小的下标，没有相等元素，则返回 -1 */
    @MethodNote("搜索等于 value 值的元素中最小的下标，没有相等元素，则返回 -1")
    public static int minEqualBS (int[] data, int value) {
        int low = 0;
        int high = data.length - 1;
        // [low, high)
        while (low < high) {
            int mid = (low + high) / 2;
            int midVal = data[mid];
            if (midVal < value)
                low = mid + 1;
            else if (midVal > value)
                high = mid - 1;
            else
                // 匹配的值需要包含
                high = mid;
        }
        // low == high
        if (data[low] == value) return low;

        return -1; // not found
    }

    /** 搜索等于 value 值的元素中最大的下标，没有相等元素，则返回 -1 */
    @MethodNote("搜索等于 value 值的元素中最大的下标，没有相等元素，则返回 -1")
    public static int maxEqualBS (int[] data, int value) {
        int low = 0;
        int high = data.length - 1;
        while (low < (high - 1)) { // high == low, or low = high - 1, data[low]==value, mid==low
            int mid = (low + high) / 2;
            int midVal = data[mid];
            if (midVal < value)
                low = mid + 1;
            else if (midVal > value)
                high = mid - 1;
            else
                // 匹配的值需要包含
                low = mid;
        }
        if (data[high] == value) return high;
        if (data[low] == value) return low;

        return -1; // not found
    }

    /** 搜索 < value 值的元素中最大的下标，没有比它小的则返回 -1 */
    @MethodNote("搜索 < value 值的元素中最大的下标，没有比它小的则返回 -1")
    public static int maxLessBS (int[] data, int value) {
        int low = 0;
        int high = data.length - 1;
        while (low < (high - 1)) { // high == low, or low = high - 1, data[low] < value, mid==low
            int mid = (low + high) / 2;
            int midVal = data[mid];
            if (midVal < value)
                low = mid;
            else high = mid - 1;
        }
        if (data[high] < value) return high;
        if (data[low] < value) return low;
        return -1; // not found
    }

    /** 搜索 > value 值的元素中最小的下标，没有比它大的则返回 -1 */
    @MethodNote("搜索 > value 值的元素中最小的下标，没有比它大的则返回 -1")
    public static int minGreaterBS (int[] data, int value) {
        int low = 0;
        int high = data.length - 1;
        while (low < high) { // high == low, when low==high-1, low can add 1
            int mid = (low + high) / 2;
            int midVal = data[mid];
            if (midVal <= value)
                low = mid + 1;
            else high = mid;
        }
        if (data[low] > value) return low;

        return -1; // not found
    }
}
