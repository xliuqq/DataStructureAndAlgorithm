package org.xliu.cs.algs_ds.algs.graph;

import org.xliu.cs.algs_ds.ds.array.UnionFindSet;
import org.xliu.cs.algs_ds.ds.graph.Edge;
import org.xliu.cs.algs_ds.ds.graph.Graph;
import org.xliu.cs.algs_ds.ds.graph.Vertex;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.*;

@ClassNote("最小生成树算法：在一个加权无向图中找到一个边的子集，使得这个子集构成的树包含图中的所有顶点，并且边的权重之和最小")
public class MinimalSpanningTree {

    @MethodNote("Prim贪心算法：从图中的某一顶点开始，逐渐长出一棵包含所有顶点的最小生成树。每一步都添加一条连接已选顶点集合和未选顶点集合的最小边。时间复杂度为O(N^2)，其中N是图中顶点的数量。")
    public static <T> List<Edge<Integer>> prim(Graph<T, Integer> graph, long startVertex) {
        int V = graph.getVertexNums();

        // 顶点序号不一定是以0开始的连续数
        HashSet<Long> existedVertexes = new HashSet<>();
        existedVertexes.add(startVertex);

        List<Edge<Integer>> results = new ArrayList<>(V - 1);


        // N个顶点，N-1条边
        // TODO: 检测图是否连通
        for (int i = 0; i < V - 1; i++) {
            Edge<Integer> minEdge = new Edge<>(0L, 0L, Integer.MAX_VALUE);
            // 对于已经存在的每个顶点，搜索不在集合中的最小的边
            for (Long existedVertex : existedVertexes) {
                // TODO: 使用最小堆优化，避免每次重复的搜索边
                for (Iterator<Edge<Integer>> it = graph.getEdges(existedVertex); it.hasNext(); ) {
                    Edge<Integer> edge = it.next();
                    long v = edge.getTo();
                    if (!existedVertexes.contains(v) && edge.getValue().compareTo(minEdge.getValue()) < 0) {
                        minEdge = edge;
                    }
                }
            }
            existedVertexes.add(minEdge.getTo());
            results.add(minEdge);
        }
        return results;
    }

    @MethodNote("Kruskal贪心算法：按照边的权重顺序（从小到大）选择边，每次选择权重最小的边加入生成树中，如果加入这条边不会形成环，则这条边是最小生成树的一部分。时间复杂度为O(MlogM)，其中M是图中边的数量")
    public static <T, E extends Comparable<E>> List<Edge<E>> kruskal(Graph<T, E> graph) {
        int V = graph.getVertexNums();
        List<Edge<E>> edges = new ArrayList<>();
        List<Edge<E>> results = new ArrayList<>(V - 1);

        // 记录已经加入 MST 的顶点，用于判断是否会形成环
        HashSet<Long> existedVertexes = new HashSet<>();

        // 将所有边加入 edges 列表
        for (Iterator<Edge<E>> it = graph.getEdges(); it.hasNext(); ) {
            edges.add(it.next());
        }

        // 按权重排序边
        edges.sort(Comparator.comparing(Edge::getValue));

        // Kruskal 算法主循环
        int edgeCount = 0;
        int i = 0;
        while (edgeCount < V - 1 && i < edges.size()) {
            Edge<E> nextEdge = edges.get(i++);
            // 如果 x 和 y 不在同一个集合中，则加入 MST
            if (!existedVertexes.contains(nextEdge.getFrom()) || !existedVertexes.contains(nextEdge.getTo())) {
                existedVertexes.add(nextEdge.getFrom());
                existedVertexes.add(nextEdge.getTo());
                edgeCount--;
                results.add(nextEdge);
            }
        }
        return results;
    }

}
