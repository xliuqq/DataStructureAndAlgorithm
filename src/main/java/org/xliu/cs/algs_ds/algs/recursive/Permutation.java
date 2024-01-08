package org.xliu.cs.algs_ds.algs.recursive;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClassNote("N个字母的所有的排列组合")
public class Permutation {
    public static List<String> getPermutationRecursion(char[] chs) {
        int len = chs.length;
        List<String> permutations = new ArrayList<>();
        getPermutationRecursion(chs, 0, len, permutations);

        return permutations;
    }

    private static void getPermutationRecursion(char[] chs, int pos, int len, List<String> permutations) {
        if (pos >= len) {
            permutations.add(Arrays.toString(chs));
            return;
        }
        char tmp;

        for (int i = pos; i < len; i++) {
            // swap chs[i] and chs[pos]
            tmp = chs[i];
            chs[i] = chs[pos];
            chs[pos] = tmp;

            getPermutationRecursion(chs, pos + 1, len, permutations);

            // swap chs[i] and chs[pos]
            chs[pos] = chs[i];
            chs[i] = tmp;
        }
    }

}