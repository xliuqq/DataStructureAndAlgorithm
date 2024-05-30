package org.xliu.cs.algs_ds.ds.graph;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

@ClassNote("数组加链表表示无向图")
public class ListGraph<T> implements Graph<T> {
    private final int numVertices;
    private final Map<Long, EdgeList> edges;
    private final Map<Long, Vertex<T>> vertexes;

    public ListGraph(Map<Long, EdgeList> edges, Map<Long, Vertex<T>> vertexes) {
        numVertices = vertexes.size();
        this.edges = edges;
        this.vertexes = vertexes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Vertex<T>[] getNeighbours(long vertexId) {
        EdgeList edgeList = edges.get(vertexId);
        if (edgeList == null) {
            return new Vertex[0];
        }
        ArrayList<Vertex<T>> neighs = new ArrayList<>();
        while (edgeList != null) {
            neighs.add(vertexes.get(edgeList.info.getTo()));
            edgeList = edgeList.next;
        }
        return neighs.toArray(new Vertex[0]);
    }

    @Override
    public Vertex<T> getVertex(long vertexId) {
        return vertexes.get(vertexId);
    }

    @Override
    public int getVertexNums() {
        return numVertices;
    }

    @Override
    public Iterator<Vertex<T>> getVertexes() {
        return vertexes.values().iterator();
    }

    @Override
    public Iterator<Edge> getEdges() {
        return edges.values().stream().flatMap(
                edgeList -> {
                    Iterator<Edge> iterator = new Iterator<Edge>() {
                        private EdgeList current = edgeList;

                        @Override
                        public boolean hasNext() {
                            return current != null;
                        }

                        @Override
                        public Edge next() {
                            Edge edge = current.info;
                            current = current.next;
                            return edge;
                        }
                    };
                    Iterable<Edge> iterable = () -> iterator;
                    return StreamSupport.stream(iterable.spliterator(), false);
                }).iterator();
    }

    @MethodNote("从边创建图（有向图），顶点和图都没有属性")
    public static Graph<Void> newListGraph(String[] edgeStr) {
        Map<Long, EdgeList> edges = new HashMap<>();
        Map<Long, Vertex<Void>> vertexes = new HashMap<>();
        for (String s : edgeStr) {
            String[] part = s.split(" ");
            long from = Long.parseLong(part[0]);
            long to = Long.parseLong(part[1]);

            vertexes.put(from, new Vertex<>(from, null));
            EdgeList fromEdge = edges.get(from);
            if (fromEdge == null) {
                edges.put(from, new EdgeList(new Edge(from, to), null));
            } else {
                fromEdge.next = new EdgeList(new Edge(from, to), fromEdge.next);
            }

            vertexes.put(to, new Vertex<>(to, null));
        }
        return new ListGraph<>(edges, vertexes);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(numVertices).append("\n");

        for (Vertex<T> v : vertexes.values()) {
            sb.append(v.getId()).append(":").append(v.getValue()).append(",");
            EdgeList head = edges.get(v.getId());
            while (head != null) {
                sb.append("->").append(head.info.getTo()).append(";");
                head = head.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class EdgeList {
        // 边的信息
        public Edge info;
        // from 顶点的边信息
        public EdgeList next;

        public EdgeList(Edge i, EdgeList n) {
            info = i;
            next = n;
        }
    }
}