package com.xliu.cs.example;

import com.xliu.cs.ds.array.UnionSet;


/**
 * 如果给拉姆一组单词，他能够迅速确定是否可以将这些单词排列在一个列表中，使得该列表中任何单词的首字母与前一单词的为字母相同。
 * 注意测试用例，假设全部为小写字母
 * <p>
 * 基本思路，有向欧拉通路，初度等于入度，除了两个顶点之外；其次还需要判断顶点是否连通（即是否存在两个不连通的通路）
 * aa, bc, cd; false，不连通
 * aa, bc, cb; false，不连通
 * ab, cd, dc; false，不连通
 * <p>
 * 连通：同样字母开头/结尾的单词进行连接，形成一条边（形成有向图），可以通过并查集判断在一个图中
 **/
public class WordHeadTail {

    /**
     * 判断是否能够构成单词排列通路
     * @param words
     * @return
     */
    public static boolean isCycleForm(String[] words) {
        UnionSet unionSet = new UnionSet(26); // 26个字母的并查集
        int[] inNums = new int[26];
        int[] outNums = new int[26];

        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(word.length() - 1) - 'a';
            int pa = unionSet.find(a);
            int pb = unionSet.find(b);
            if (pa != pb) {
                unionSet.union(pa, pb);
            }
            inNums[b]++;  // 入度加1
            outNums[a]++; // 出度加1
        }

        // 多个集合，不能形成通路
        int setSizes = unionSet.countSet();
        int notShownLetters = 26;
        for (int i = 0; i < 26; i++) {
            if (inNums[i] > 0 || outNums[i] > 0) {
                notShownLetters--;
            }
        }
        // 包含未出现的字母，会单独计算为一个集合
        if (setSizes - notShownLetters > 1) {
            return false;
        }

        int cnt = 0;
        for (int i = 0; i < inNums.length; i++) {
            // 欧拉通路，只允许开始/结尾的入度和出度不一致
            if (inNums[i] != outNums[i]) {
                cnt++;
                if (cnt > 2) return false;
            }
        }

        return true;
    }

}
