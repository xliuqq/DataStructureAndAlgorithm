package org.xliu.cs.algs_ds.algs.bit;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

/**
 * Use bit map to sort integer array(no repeat numbers), 10,000,000 integers only use 1.25MB memory.
 * Each Byte represents 8 integers.
 */
@ClassNote("位图排序")
public class BitMapSort {

    /**
     * bit map sort, the value of data must be no less than 0, no greater than maxNumber
     *
     * @param data      : sorted data, type Int
     * @param maxNumber : the maximum number of the data
     */
    public static void sort(int[] data, int maxNumber) {
        byte[] bitMap = new byte[maxNumber / 8 + 1];

        // set bits
        int bitMapIndex;
        int bitMapOffset;
        for (int value : data) {
            bitMapIndex = value >> 3;        //Each byte represent 8 numbers, right shift 3 digits
            bitMapOffset = value & 0x07;
            bitMap[bitMapIndex] |= 1 << bitMapOffset;
        }

        // traverse the number, re-assgin value to array
        int cnt = 0;
        for (int i = 0; i < maxNumber; i++) {
            if ((bitMap[i >> 3] & (1 << (i & 0x07))) != 0) {
                data[cnt] = i;
                cnt++;
            }
        }
    }

}
