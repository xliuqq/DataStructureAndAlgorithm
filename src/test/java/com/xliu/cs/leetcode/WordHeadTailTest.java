package com.xliu.cs.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordHeadTailTest {

    @Test
    void isCycleForm() {
        String[] words = new String[]{"ab"};
        assertTrue(WordHeadTail.isCycleForm(words));

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