package com.xliu.cs.ds.graph;

public class ListGraph {
    private final int numVertices;
    private final EdgeList[] edges;

    public ListGraph(int vers, EdgeList[] edges) {
        numVertices = vers;
        this.edges = edges;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(numVertices).append("\n");
        for (int i = 0; i < numVertices; i++) {
            EdgeList head = edges[i];
            while (head != null) {
                sb.append(i).append("->").append(head.info.to).append(":").append(head.info.val).append("\n");
                head = head.next;
            }
        }
        return sb.toString();
    }

    public static class EdgeList {
        public final EdgeInfo info;
        public final EdgeList next;

        public EdgeList(EdgeInfo i, EdgeList n) {
            info = i;
            next = n;
        }
    }

    public static class EdgeInfo {
        public final int from;
        public final int to;
        public final int val;

        public EdgeInfo(int f, int t) {
            this(f, t, 0);
        }

        public EdgeInfo(int f, int t, int v) {
            from = f;
            to = t;
            val = v;
        }
    }
}