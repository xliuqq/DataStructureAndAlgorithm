package org.xliu.cs.algs_ds.algs.search;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.*;

/**
 * 倒水问题。
 */
@ClassNote("倒水问题：给定三个分别是3L,5L,8L的桶，其中8L的桶装满水，如何倒水生成两个4L的水桶？")
public class PourWater {
    private State state;

    private final Deque<State> states = new ArrayDeque<>();


    public PourWater(State state) {
        this.state = state;
        states.addLast(state);
    }

    // 初始状态
    private static class State {
        private final List<Bucket> buckets;
        private final int[] finishedStore;

        public State(List<Bucket> buckets, int[] finishedStore) {
            this.buckets = buckets;
            this.finishedStore = finishedStore;
        }

        private int size() {
            return buckets.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(buckets, state.buckets);
        }

        @Override
        public int hashCode() {
            return Objects.hash(buckets);
        }

        public State dump(int from, int to) {
            // 复制状态
            List<Bucket> newBuckets = new ArrayList<>(buckets.size());
            for (Bucket bucket : buckets) {
                Bucket newBucket = new Bucket(bucket.cap, bucket.store);
                newBuckets.add(newBucket);
            }
            boolean isAdd = newBuckets.get(to).add(newBuckets.get(from));
            assert isAdd;

            return new State(newBuckets, finishedStore);
        }

        public boolean canDumpWater(int from, int to) {
            // from 不空，且 to 不满
            return !buckets.get(from).isEmpty() && !buckets.get(to).isFull();
        }

        public boolean isFinished() {
            int cnt = 0;
            for (Bucket bucket : buckets) {
                if (bucket.store == finishedStore[cnt]) {
                    cnt++;
                }

                if (cnt == finishedStore.length) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "{" + buckets + '}';
        }
    }

    private static class Bucket {
        // 容量
        int cap;
        // 当前存储
        int store;

        public Bucket(int cap, int store) {
            this.cap = cap;
            this.store = store;
        }

        // 加水，返回的结果表示是否允许该操作。
        public boolean add(Bucket from) {
            // 没水
            if (from.isEmpty()) {
                return false;
            }
            // 满了
            if (isFull()) {
                return false;
            }
            // 可以倒的水量
            int amount = Math.min(from.store, this.cap - this.store);

            store += amount;
            from.store -= amount;

            return true;
        }

        public boolean isEmpty() {
            return store == 0;
        }

        public boolean isFull() {
            return store == cap;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bucket bucket = (Bucket) o;
            return cap == bucket.cap && store == bucket.store;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cap, store);
        }

        @Override
        public String toString() {
            return cap + ":" + store;
        }
    }

    private boolean isDuplicatedState(State state) {
        return states.contains(state);
    }

    public void traverse() {
        int size = state.size();

        if (state.isFinished()) {
            print();
            return;
        }

        //循环里面不能退出，否则后续的搜索就无法寻找。
        for (int fromIndex = 0; fromIndex < size; fromIndex++) {
            for (int toIndex = 0; toIndex < size; toIndex++) {
                if (fromIndex == toIndex) {
                    continue;
                }
                if (!state.canDumpWater(fromIndex, toIndex)) {
                    continue;
                }
                State newState = state.dump(fromIndex, toIndex);

                if (!isDuplicatedState(newState)) {
                    states.addLast(newState);
                    State tmp = state;
                    state = newState;
                    traverse();
                    // 重置
                    state = tmp;
                    states.removeLast();
                }
            }
        }

    }

    private void print() {
        for (State s : states) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] finishedStore = new int[]{4, 4};
        new PourWater(new State(
                Arrays.asList(new Bucket(3, 0), new Bucket(5, 0), new Bucket(8, 8)),
                finishedStore
        )).traverse();
    }

}
