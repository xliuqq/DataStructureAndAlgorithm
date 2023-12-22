package org.xliu.cs.algs_ds.leetcode.graph;

import org.xliu.cs.algs_ds.leetcode.graph.WordHeadTail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordHeadTailTest {

    @Test
    void isCycleForm() {
        String[] words = new String[]{"ab"};
        Assertions.assertTrue(WordHeadTail.isCycleForm(words));

        words = new String[]{"aa", "bc", "cb"};
        assertFalse(WordHeadTail.isCycleForm(words));

        words = new String[]{"ab", "bc", "cd"};
        assertTrue(WordHeadTail.isCycleForm(words));

        words = new String[]{"aa", "bc", "cd"};
        assertFalse(WordHeadTail.isCycleForm(words));

        words = new String[]{"ab", "cd", "dc"};
        assertFalse(WordHeadTail.isCycleForm(words));
    }
}