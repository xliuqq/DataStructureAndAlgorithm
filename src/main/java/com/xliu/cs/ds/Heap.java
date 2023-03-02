package com.xliu.cs.ds;

import java.util.Arrays;

/**
 * 堆的实现, the head is the min value。
 * Java中堆的实现为 {@link java.util.PriorityQueue}
 */
@SuppressWarnings("unchecked")
public class Heap<E extends Comparable<? super E>> {

    /*
     * 0's children is 1 and 2
     * 1's children is 3 and 4
     * 2's children is 5 and 6
     * 3's children is 7 and 8
     * 4's children is 9 and 10
     *
     * x's child is x * 2 + 1 and x * 2 + 2
     * x's parent is (x - 1) / 2
     */
    private Object[] elems;

    private int size = 0;


    public Heap() {
        this(4);
    }

    public Heap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0");
        }

        elems = new Object[initialCapacity];
    }

    public void add(E e) {
        if (size >= elems.length) {
            expand();
        }
        siftUp(e, size, elems);
        size++;
    }

    /**
     * sift the last element up
     */
    private static <E> void siftUp(E e, int index, Object[] elems) {
        // we first set the element, The PriorityQueue last set the element.
        elems[index] = e;

        while (index > 0) { // or != 0
            int parentIndex = (index - 1) >>> 1;
            Comparable<? super E> currentValue = (Comparable<? super E>) elems[index];

            Object p = elems[parentIndex];
            if (currentValue.compareTo((E) p) >= 0) {
                break;
            }
            elems[index] = p;
            elems[parentIndex] = currentValue;

            index = parentIndex;
        }
    }

    private void expand() {
        int oldCapacity = elems.length;
        if (oldCapacity == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("heap size is too large");
        }
        /** see {@link java.util.PriorityQueue#grow(int)} for better calculating new capacity*/
        int newCapacity;
        if (oldCapacity < (Integer.MAX_VALUE / 2)) {
            newCapacity = 2 * oldCapacity;
        } else {
            newCapacity = Integer.MAX_VALUE;
        }

        elems = Arrays.copyOf(elems, newCapacity);
    }

    public E poll() {
        if (size == 0) {
            return null;
        }

        E head = (E) elems[0];

        int n = --size;
        E x = (E)elems[n];

        // set the last element null, prevent memory leak
        elems[n] = null;

        if (n > 0) {
            siftDown(x, n, elems);
        }

        return head;
    }

    private static <E> void siftDown(E e, int size, Object[] elems) {
        int index = 0;
        // set the first element with the last position value.
        elems[index] = e;

        // index starts with 0
        // when size == 1, <= (size - 2) / 2, is not the same as (<= size/2 - 1) === ( < size / 2 )
        int half = size >>> 1;

        // from top to down
        while (index < half) {
            int leftChild = index * 2 + 1;
            int rightChild = leftChild + 1;

            Comparable<? super E> value = (Comparable<? super E>) elems[index];

            if (rightChild > size - 1) {
                // only has one child, this child has no more children
                if (value.compareTo((E) elems[leftChild]) >= 0) {
                    elems[index] = elems[leftChild];
                    elems[leftChild] = value;
                }
                break;
            } else {
                // has two children
                Comparable<? super E> leftValue = (Comparable<? super E>) elems[leftChild];

                // current value is the least
                if (value.compareTo((E) elems[leftChild]) < 0 && value.compareTo((E) elems[rightChild]) < 0) {
                    break;
                }

                // left child is the least
                if (leftValue.compareTo((E) elems[rightChild]) < 0) {
                    elems[index] = leftValue;
                    elems[leftChild] = value;
                    index = leftChild;
                } else {
                    // right child is the least
                    elems[index] = elems[rightChild];
                    elems[rightChild] = value;
                    index = rightChild;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }
}
