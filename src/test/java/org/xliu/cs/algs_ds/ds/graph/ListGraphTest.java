package org.xliu.cs.algs_ds.ds.graph;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListGraphTest {

    @Test
    public void testListGraph() {
        String[] edgeStr = "0 1;1 2;2 3;4 5;1 4".split(";");

        Graph<Void> graph = ListGraph.newListGraph(edgeStr);

        assertEquals(6, graph.getVertexNums());

        AtomicInteger count = new AtomicInteger();
        graph.getEdges().forEachRemaining(e -> count.getAndIncrement());
        assertEquals(5, count.get());

        System.out.println(graph);

    }
}