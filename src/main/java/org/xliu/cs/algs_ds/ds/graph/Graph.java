package org.xliu.cs.algs_ds.ds.graph;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.Iterator;

/**
 * 假设顶点类型是 int（从 0 -> n）
 */
@ClassNote("图抽象类")
public interface Graph<T> {

    /**
     * 获取 v 的邻接点（对于有向图，获取的是其指向的节点）
     */
    Vertex<T>[] getNeighbours(long vertexId);

    Vertex<T> getVertex(long vertexId);

    /**
     * 获取顶点的数量
     * @return int
     */
    int getVertexNums();


    /**
     * 获取所有的顶点（不可改）
     */
    Iterator<Vertex<T>> getVertexes();

    /**
     * 获取所有的边
     */
    Iterator<Edge> getEdges();


}
