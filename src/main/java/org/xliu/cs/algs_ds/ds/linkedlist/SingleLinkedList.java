package org.xliu.cs.algs_ds.ds.linkedlist;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

/**
 * 单链表
 */
@ClassNote("单向链表")
public class SingleLinkedList<T> {
    private SingleNode<T> first;
    private SingleNode<T> last;
    private int size;

    public SingleLinkedList() {
    }

    public void addLast(T value) {
        final SingleNode<T> newNode = new SingleNode<>(value);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void addFirst(T value) {
        final SingleNode<T> newNode = new SingleNode<>(value);
        if (first == null) {
            last = newNode;
        } else {
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (first == null) {
            return null;
        }
        SingleNode<T> tmp = first;
        first = first.next;
        size--;

        return tmp.val;
    }


    public String toString() {
        SingleNode<T> head = first;
        StringBuilder sb = new StringBuilder();
        int cnt = 10;
        while (head != null && cnt > 0) {
            sb.append(head.val).append(" ");
            head = first.next;
            cnt--;
        }
        if (cnt == 0) {
            sb.append(", more [...] ");
        }
        return sb.toString();
    }

    /**
     * 单链表节点
     */
    static class SingleNode<T> {
        public final T val;
        public SingleNode<T> next;

        public SingleNode(T x) {
            val = x;
        }

        public SingleNode(T x, SingleNode n) {
            val = x;
            next = n;
        }
    }

}
