package com.xliu.cs.ds.graph;

import org.junit.jupiter.api.Test;

class ListGraphTest {

    @Test
    public void testListGraph() {
        int numVertices = 6;
        String[] edgeStr = "0 1;1 2;2 3;4 5;1 4".split(";");

        ListGraph graph = ListGraph.newListGraph(numVertices, edgeStr);

        System.out.println(graph);

    }
}