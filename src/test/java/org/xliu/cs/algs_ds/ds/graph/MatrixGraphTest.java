package org.xliu.cs.algs_ds.ds.graph;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.graph.MatrixGraph;

class MatrixGraphTest {

    @Test
    void newMatrixGraph() {
        int numVertices = 6;
        String[] edgeStr = "0 1;1 2;2 3;4 5;1 4".split(";");
        MatrixGraph graph = MatrixGraph.newMatrixGraph(numVertices, edgeStr);

        System.out.println(graph);

    }
}