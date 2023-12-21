package com.xliu.cs.ds.graph;

import com.xliu.cs.generate.ClassNote;

@ClassNote("数组加链表表示图")
public class ListGraph {
    private final int numVertices;
    private final EdgeList[] edges;

    public ListGraph(int vers, EdgeList[] edges) {
        numVertices = vers;
        this.edges = edges;
    }

    public static ListGraph newListGraph(int numVertices, String[] edgeStr) {
        ListGraph.EdgeList[] edges= new ListGraph.EdgeList[numVertices];
        for (String s : edgeStr) {
            String[] part = s.split(" ");
            int from = Integer.parseInt(part[0]);
            int to = Integer.parseInt(part[1]);

            if (edges[from] == null) {
                edges[from] = new EdgeList(new EdgeInfo(from, to), null);
            } else {
                edges[from].next = new EdgeList(new EdgeInfo(from, to), edges[from].next);
            }
            if (edges[to] == null) {
                edges[to] = new EdgeList(new EdgeInfo(to, from), null);
            } else {
                edges[to].next = new EdgeList(new EdgeInfo(to, from), edges[to].next);
            }
        }
        return new ListGraph(numVertices, edges);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(numVertices).append("\n");
        for (int i = 0; i < numVertices; i++) {
            EdgeList head = edges[i];
            while (head != null) {
                sb.append(i).append("->").append(head.info.to).append(":").append(head.info.val).append(",");
                head = head.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class EdgeList {
        // 边的信息
        public EdgeInfo info;
        // from 顶点的边信息
        public EdgeList next;

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