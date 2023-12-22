package org.xliu.cs.algs_ds.leetcode.dp;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * 一个只包含'A'、'B'和'C'的字符串，如果存在某一段长度为3的连续子串中恰好'A'、'B'和'C'各有一个，那么这个字符串就是纯净的，
 * 否则这个字符串就是暗黑的。例如：
 * BAACAACCBAAA 连续子串"CBA"中包含了'A','B','C'各一个，所以是纯净的字符串
 * AABBCCAABB 不存在一个长度为3的连续子串包含'A','B','C',所以是暗黑的字符串
 * 你的任务就是计算出长度为n的任意字符串(只包含'A'、'B'和'C')，有多少个是暗黑的字符串。
 * 1）假设长度为 n 的字符串中，暗黑字符串总数数为 f(n)
 * 2）假设长度为 n 的暗黑字符串中，其最后两位一样的个数为 s(n)，不一样的个数为 d(n)，则 f(n) = s(n) + d(n)
 * 3）长度为n的暗黑字符串，只能由长度为n-1的暗黑字符串最后拼上一个字符且仍是暗黑，f(n) = 3 * s(n-1) + 2 * d(n-1) = 2 * f(n-1) + s(n-1)
 * 4) s(n) = s(n-1) + d(n-1) = f(n-1)
 * 5) f(n) =  2 * f(n-1) + s(n-1) = 2 * f(n-1) + f(n-2)
 */
@ClassNote("暗黑字符串")
public class BlackString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int prev2 = 3, prev1 = 9;
            int res = 0;
            for (int i=3; i<=n; i++) {
                res = 2 * prev1 + prev2;
                prev2 = prev1;
                prev1 = res;
            }
            System.out.println(res);
        }
    }
}
