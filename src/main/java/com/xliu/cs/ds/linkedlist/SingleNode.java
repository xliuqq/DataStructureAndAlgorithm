package com.xliu.cs.ds.linkedlist;

/**
 * 单链表节点
 */
public class SingleNode<T> {
    public final T val;
    public SingleNode<T> next;

    public SingleNode(T x) { val = x; }

    public SingleNode(T x, SingleNode n) { val = x; next = n;}
}

