package com.xliu.cs.ds.graph;

import com.xliu.cs.generate.ClassNote;

/**
 * 矩阵表示图（稠密图）
 */
@ClassNote("矩阵表示图（稠密图）")
public class MatrixGraph {

    // 顶点个数
    private final int numVertices;
    // 顶点对应的边
    private final int[][] edges;

    private MatrixGraph(int vers, int[][] edges) {
        numVertices = vers;
        this.edges = edges;
    }

    /**
     * read undirected graph from System.in in matrix representing
     * Format:
     * numberOfVertices numberOfEdges
     * Edge1
     * Edge2
     * ...
     * <p>
     * The Edge format is `v w`
     */
    public static MatrixGraph newMatrixGraph(int numberOfVertices, String[] edgeStr) {
        int[][] edges = new int[numberOfVertices][numberOfVertices];
        int cnt = 0;
        for (String edge : edgeStr) {
            String[] part = edge.split(" ");
            int from = Integer.parseInt(part[0]);
            int to = Integer.parseInt(part[1]);
            edges[from][to] = 1;
            edges[to][from] = 1;
        }

        return new MatrixGraph(numberOfVertices, edges);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(numVertices).append("\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = i; j < numVertices; j++) {
                if (edges[i][j] != 0) {
                    sb.append(i).append("->").append(j).append(",");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
