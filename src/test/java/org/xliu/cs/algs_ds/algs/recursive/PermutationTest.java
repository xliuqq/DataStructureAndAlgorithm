package org.xliu.cs.algs_ds.algs.recursive;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermutationTest {

    @Test
    void getPermutationRecursion() {
        char[] chs = new char[]{'A', 'B', 'C', 'D'};
        List<String> permutationRecursion = Permutation.getPermutationRecursion(chs);
        assertEquals(24, permutationRecursion.size());
    }
}