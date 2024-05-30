package org.xliu.cs.algs_ds.algs.graph;

import org.xliu.cs.algs_ds.ds.graph.Edge;
import org.xliu.cs.algs_ds.ds.graph.Graph;
import org.xliu.cs.algs_ds.ds.graph.Vertex;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@ClassNote("图操作类")
public class GraphOp<T> {
    private enum VisitState {
        NOT_VISIT, // 顶点未被发现
        VISITING,  // 顶点被发现但还没处理完
        VISITED;   // 被发现且被处理完
    }

    private final Graph<T> graph;

    public GraphOp(Graph<T> graph) {
        this.graph = graph;
    }

    @MethodNote("有向图从特定点广度遍历")
    public void bfs(long startVertexId, Function<Vertex<T>, Void> vertexConsumer) {
        // 初始化所有节点未访问
        Map<Long, VisitState> visitStates = new HashMap<>();
        bfsInternal(startVertexId, visitStates, vertexConsumer);
    }

    @MethodNote("有向图广度遍历")
    public void bfs(Function<Vertex<T>, Void> vertexConsumer) {
        Map<Long, Integer> vertexInDegree = getVertexInDegree();
        Set<Long> zeroInDegree = vertexInDegree.entrySet().stream()
                .filter(v -> v.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(TreeSet::new, Set::add, Set::addAll);


        // 对于有向图，两个入度为0的顶点，可能会有公共的子节点，所以要用全局的状态
        // 对于无向图，两个入度为0的顶点，一定不会有公共子节点（不然可以沿着到达），因此可以不用全局的状态，调用 bfs(vId, vertexConsumer)
        Map<Long, VisitState> visitStates = new HashMap<>();
        for (Long vId : zeroInDegree) {
            bfsInternal(vId, visitStates, vertexConsumer);
        }
    }

    public void bfsInternal(long startVertexId, Map<Long, VisitState> visitStates, Function<Vertex<T>, Void> consumer) {
        Queue<Long> pending = new ArrayDeque<>();

        visitStates.put(startVertexId, VisitState.VISITING);
        pending.offer(startVertexId);

        while (!pending.isEmpty()) {
            long v = pending.poll();
            Vertex<T>[] neighbours = graph.getNeighbours(v);

            for (Vertex<T> w : neighbours) {
                long id = w.getId();
                if (visitStates.get(id) == null) {
                    visitStates.put(id, VisitState.VISITING);
                    pending.offer(w.getId());
                    //Process tree edge v -> w
                }
            }
            //Process vertex v here
            consumer.apply(graph.getVertex(v));

            visitStates.put(v, VisitState.VISITED);
        }
    }

    /**
     * 有向图的深度遍历：
     * 如果w在边vw处理时未被发现，则边vw叫做树边(tree edge)，v是w的父亲；
     * 如果w是v的祖先，则vw叫做回退边(back edge)；
     * 如果w是v的后代，但w早于边vw处理时被发现，则vw被称作下降边(descendant/forward edge)；
     * 如果w和v不是祖先与后代的关系，则边vw被称作交叉边(cross edge);
     */
    @MethodNote("有向图从特定点深度遍历")
    public void dfs(long vId, Function<Vertex<T>, Void> vertexConsumer) {
        // 初始化所有节点未访问
        Map<Long, VisitState> visitStates = new HashMap<>();
        dfsInternal(vId, visitStates, vertexConsumer);
    }

    @MethodNote("有向图深度遍历")
    public void dfs(Function<Vertex<T>, Void> vertexConsumer) {
        Map<Long, Integer> vertexInDegree = getVertexInDegree();
        Set<Long> zeroInDegree = vertexInDegree.entrySet().stream()
                .filter(v -> v.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(TreeSet::new, Set::add, Set::addAll);

        // 对于有向图，两个入度为0的顶点，可能会有公共的子节点，所以要用全局的状态
        // 对于无向图，两个入度为0的顶点，一定不会有公共子节点（不然可以沿着到达），因此可以不用全局的状态，调用 dfs(vId, vertexConsumer)
        Map<Long, VisitState> visitStates = new HashMap<>();
        for (Long vId : zeroInDegree) {
            dfsInternal(vId, visitStates, vertexConsumer);
        }
    }

    public void dfsInternal(long vId, Map<Long, VisitState> visitStates, Function<Vertex<T>, Void> vertexConsumer) {
        visitStates.put(vId, VisitState.VISITING);
        // Preorder processing of vertex v
        Vertex<T>[] neighbours = graph.getNeighbours(vId);

        for (Vertex<T> w : neighbours) {
            VisitState state = visitStates.get(w.getId());
            if (state == null) {
                // Exploratory processing for tree edge vw;
                dfsInternal(w.getId(), visitStates, vertexConsumer);
                // Backtrack processing for tree edge vw, using wAns(like inorder)
            } else {
                // Checking(i.e., processing) for nontree edge vw
                // if state == VISITING, the graph has a ring.
            }
        }
        visitStates.put(vId, VisitState.VISITED);
        // Postorder processing of vertex v
        vertexConsumer.apply(graph.getVertex(vId));
    }


    /**
     * Kahn算法：得到拓扑序，判断有向图是否无环；
     * 对每个顶点计算入度（一次遍历），维持入度为0的顶点集合S，对集合中顶点，对其邻边顶点入度减一，判断为0加入S；最后若还有入度不为0的顶点，则存在环；
     *
     * @return boolean 是否存在环
     */
    @MethodNote("拓扑序（有向图）: Kahn算法，可以判断是否有环，类似于广度遍历")
    public boolean topologyByKahn(List<Long> orders) {
        Map<Long, Integer> vertexInDegree = getVertexInDegree();

        Set<Long> zeroInDegree = vertexInDegree.entrySet().stream()
                .filter(v -> v.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        while (!zeroInDegree.isEmpty()) {
            orders.addAll(zeroInDegree);

            for (Long vId : zeroInDegree) {
                vertexInDegree.remove(vId);

                for (Vertex<T> neighbour : graph.getNeighbours(vId)) {
                    vertexInDegree.computeIfPresent(neighbour.getId(), (k, v) -> v - 1);
                }
            }
            // 重新计算入度为0的顶点
            zeroInDegree = vertexInDegree.entrySet().stream()
                    .filter(v -> v.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
        }

        return !vertexInDegree.isEmpty();
    }

    /**
     * 只能对DAG得到拓扑序，无法判断是否有环。
     * DFS 算法本身是可以判断是否环的。
     */
    @MethodNote("拓扑序（有向图）: DFS，无法判断是否有环")
    public void topologyByDfs(List<Long> orders) {
        dfs(v -> {
            orders.add(v.getId());
            return null;
        });
    }


    @MethodNote("获取每个顶点的入度")
    public Map<Long, Integer> getVertexInDegree() {
        Map<Long, Integer> vertexInDegree = new HashMap<>();
        Iterator<Vertex<T>> vertexes = graph.getVertexes();
        while (vertexes.hasNext()) {
            vertexInDegree.put(vertexes.next().getId(), 0);
        }

        Iterator<Edge> edges = graph.getEdges();

        while (edges.hasNext()) {
            Edge edge = edges.next();
            // 点都应该已存在
            vertexInDegree.computeIfPresent(edge.getTo(), (k, v) -> v + 1);
        }

        return vertexInDegree;
    }

}
