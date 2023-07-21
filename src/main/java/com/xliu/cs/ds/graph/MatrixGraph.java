package com.xliu.cs.ds.graph;

/**
 * 矩阵表示图（稠密图）
 */
public class MatrixGraph {

    // 顶点个数
    private final int numVertices;
    // 顶点对应的边
    private final int[][] edges;

    public MatrixGraph(int vers, int[][] edges) {
        numVertices = vers;
        this.edges = edges;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(numVertices).append("\n");
        for (int i=0; i<numVertices; i++) {
            for (int j=i; j<numVertices; j++) {
                if (edges[i][j] != 0) {
                    sb.append(i).append("->").append(j).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
