package org.xliu.cs.algs_ds.ds.graph;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListGraphTest {

    @Test
    public void testListGraph() {
        String[] edgeStr = "0 1;1 2;2 3;4 5;1 4".split(";");

        Graph<Void, Void> graph = ListGraph.newDirectedListGraph(edgeStr);

        assertEquals(6, graph.getVertexNums());

        AtomicInteger count = new AtomicInteger();
        graph.getEdges().forEachRemaining(e -> count.getAndIncrement());
        assertEquals(5, count.get());

        System.out.println(graph);

    }

    @Test
    public void testListGraphWithEdgeValue() {
        String[] edgeStr = "0 1 1;1 2 2;2 3 3;4 5 4;1 4 1".split(";");

        Graph<Void, Integer> graph = ListGraph.newUnDirectedGraphWithEdgeValue(edgeStr);

        assertEquals(6, graph.getVertexNums());

        AtomicInteger count = new AtomicInteger();
        graph.getEdges().forEachRemaining(e -> count.getAndIncrement());
        assertEquals(10, count.get());

        Iterator<Edge<Integer>> edges = graph.getEdges(1);
        // 判断 edges 的长度为 3
        int vertextOneCount = 0;
        while (edges.hasNext()) {
            edges.next();
            vertextOneCount ++;
        }
        assertEquals(3, vertextOneCount);

        System.out.println(graph);

    }
}