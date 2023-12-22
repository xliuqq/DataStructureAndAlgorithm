package org.xliu.cs.algs_ds.algs.math;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

@ClassNote("完美洗牌")
public class PerfectShuffle {
    /**
     * 有个长度为2n的数组{a1,a2,a3,…,an,b1,b2,b3,…,bn}，希望排序后{b1, a1, b2, a2,…., bn, an}，请考虑有无时间复杂度O(n)，空间复杂度O(1)的解法.
     * 又称为“完美洗牌”.
     */
    public static <T> void inplaceInShuffle(T[] data) {
        int len = data.length;
        if (len == 1) {
            return;
        }
        if (len % 2 != 0) {
            System.err.println("输入的数组的长度必须是偶数!");
            return;
        }
        innerPerfectShuffle(data, 0, len);
    }

    private static <T> void innerPerfectShuffle(T[] data, int from, int to) {
        int len = to - from;

        // 判断长度是不是 3^k - 1
        int k = 0;
        int initial = len + 1;
        while (initial > 2) {
            initial /= 3;
            k += 1;
        }

        int perfectLength = (int) Math.pow(3, k) - 1;
        boolean isPerfectLength = perfectLength == len;

        if (isPerfectLength) {
            perfectShuffle(data, k, len, from);
            return;
        }

        int m = perfectLength >> 1;
        int n = len >> 1;

        // 将 m 到 m+n 的数组循环左移n-m次（通过三次数组反转实现）
        reverse(data, from + m, from + m + n);
        reverse(data, from + 2 * m, from + m + n);
        reverse(data, from + m, from + 2 * m);

        perfectShuffle(data, k, perfectLength, from);

        innerPerfectShuffle(data, from + perfectLength, to);
    }

    private static <T> void reverse(T[] data, int from, int to) {
        for (int start = from, end = to - 1; start < end; start++, end--) {
            T tmp = data[start];
            data[start] = data[end];
            data[end] = tmp;
        }
    }

    private static <T> void perfectShuffle(T[] data, int k, int len, int offset) {
        assert Math.pow(3, k) - 1 == len;

        // len = 3 ^ k - 1, start position from 1, 3, 3 ^ k
        int startPosition = 1;
        for (int i = 0; i < k; i++) {
            int currentPosition = startPosition;
            T oldVaLue = data[offset + currentPosition - 1];
            T curretValue;
            do {
                currentPosition = (2 * currentPosition) % (len + 1);
                // 将当前位置上的值，放入目标位置
                curretValue = data[offset + currentPosition - 1];
                data[offset + currentPosition - 1] = oldVaLue;
                oldVaLue = curretValue;
            } while (currentPosition != startPosition);

            startPosition *= 3;
        }
    }
}
