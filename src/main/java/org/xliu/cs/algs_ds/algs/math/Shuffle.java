package org.xliu.cs.algs_ds.algs.math;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.Random;

/**
 *
 * 随机洗牌算法 Fisher-Yates shuffle.
 * Shuffle an array of n elements, the probability of each element appearing at each position is the same.
 */
@ClassNote("均匀洗牌")
public class Shuffle {

    private static final Random RANDOM = new Random();

    public static <T> void shuffle(T[] data) {
        // 从 0 到 n - 2 进行交换
        // 选出第一张和任意一个1到n的牌换；选出第二张和任意2-n的牌换；依次直到n-1选出n-1到n的牌换；共n-1次交换；
        for (int i = 0, len = data.length; i < len - 1; i++) {
            int position = RANDOM.nextInt(len - i) + i;
            T tmp = data[position];
            data[position] = data[i];
            data[i] = tmp;
        }
    }

    public static <T> void shuffle2(T[] data) {
        // 或者从 n-1 到 1 进行交换
        for (int i = data.length - 1; i > 0; i--) {
            int position = RANDOM.nextInt(i + 1);
            T tmp = data[position];
            data[position] = data[i];
            data[i] = tmp;
        }
    }


}
