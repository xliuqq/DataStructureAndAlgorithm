package org.xliu.cs.algs_ds.algs.recursive;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.recursive.RecursiveToFor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class RecursiveToForTest {


    @Test
    public void testRecursive() {
        List<String> orders = RecursiveToFor.hanio(1, 'A', 'B', 'C');
        assertIterableEquals(Collections.singletonList("A-C"), orders);


        orders = RecursiveToFor.hanio(2, 'A', 'B', 'C');
        assertIterableEquals(Arrays.asList("A-B", "A-C", "B-C"), orders);


        orders = RecursiveToFor.hanio(3, 'A', 'B', 'C');
        assertIterableEquals(Arrays.asList("A-C", "A-B", "C-B", "A-C", "B-A", "B-C", "A-C"), orders);
    }

    @Test
    public void testNonRecursive() {
        assertIterableEquals(RecursiveToFor.hanio(1, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(1, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(2, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(2, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(3, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(3, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(4, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(4, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(5, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(5, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(6, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(6, 'A', 'B', 'C'));
        assertIterableEquals(RecursiveToFor.hanio(7, 'A', 'B', 'C'), RecursiveToFor.hanioNonRecursive(7, 'A', 'B', 'C'));

    }
}