package com.xliu.cs.algs.recursive;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class RecursiveToForTest {


    @Test
    public void testRecursive() {
        List<String> orders = RecursiveToFor.hanio(1, 'A', 'B', 'C');
        assertIterableEquals(List.of("A-C"), orders);

        orders = RecursiveToFor.hanio(2, 'A', 'B', 'C');
        assertIterableEquals(List.of("A-B", "A-C", "B-C"), orders);


        orders = RecursiveToFor.hanio(3, 'A', 'B', 'C');
        assertIterableEquals(List.of("A-C", "A-B", "C-B", "A-C", "B-A", "B-C", "A-C"), orders);
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