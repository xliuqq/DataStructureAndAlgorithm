package org.xliu.cs.algs_ds.algs.graph;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.ds.graph.Edge;
import org.xliu.cs.algs_ds.ds.graph.Graph;
import org.xliu.cs.algs_ds.ds.graph.ListGraph;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MinimalSpanningTreeTest {

    @Test
    void prim() {
        // 生成测试用例
        String[] edges = new String[]{
                "0 1 1",
                "0 2 2",
                "0 3 3",
                "1 2 4",
                "1 3 5",
                "2 3 6",
                "2 4 7",
                "3 4 8",
                "3 5 9",
                "4 5 10",
                "4 6 11"
        };
        Graph<Void, Integer> graph = ListGraph.newUnDirectedGraphWithEdgeValue(edges);
        List<Edge<Integer>> prim = MinimalSpanningTree.prim(graph, 0);
        for (Edge<Integer> edge : prim) {
            System.out.println(edge.getFrom() + "->" + edge.getTo() + " " + edge.getValue());
        }
    }

    @Test
    void kruskal() {
        // 生成测试用例
        String[] edges = new String[]{
                "0 1 1",
                "0 2 2",
                "0 3 3",
                "1 2 4",
                "1 3 5",
                "2 3 6",
                "2 4 7",
                "3 4 8",
                "3 5 9",
                "4 5 10",
                "4 6 11"
        };
        Graph<Void, Integer> graph = ListGraph.newUnDirectedGraphWithEdgeValue(edges);
        List<Edge<Integer>> kruskal = MinimalSpanningTree.kruskal(graph);
        List<Edge<Integer>> prim = MinimalSpanningTree.prim(graph, 0);

        kruskal.sort(Comparator.comparingLong((Edge<Integer> e) -> e.getFrom()).thenComparingLong(Edge::getTo));
        prim.sort(Comparator.comparingLong((Edge<Integer> e) -> e.getFrom()).thenComparingLong(Edge::getTo));
        assertEquals(kruskal, prim);
    }
}