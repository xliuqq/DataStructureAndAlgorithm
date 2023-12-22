package org.xliu.cs.algs_ds.leetcode.heap;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

import java.util.*;

/**
 * 考察点：Heap Sort
 * <p/>
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 *      You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *      Your codes algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * Note 2:
 *      same count, return smaller value;
 *      return result no order;
 * */
@ClassNote("频率最高的K个元素")
public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {

        // HashMap O(n)
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        // use frequency array, which is O(Max(frequency)) with much more memory

        // Here use min Heap, with K capacity, O(nlgk)
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(k,
                Comparator.comparingInt(Map.Entry::getValue));
        // PriorityQueue automatically extends its size, keep its size with k
        for (Map.Entry<Integer, Integer> kv : count.entrySet()) {
            if (maxHeap.size() == k && Objects.requireNonNull(maxHeap.peek()).getValue() < kv.getValue()) {
                maxHeap.poll();
                maxHeap.add(kv);
            } else if (maxHeap.size() < k) {
                maxHeap.add(kv);
            }
        }

        List<Integer> res = new LinkedList<>();
        for (Map.Entry<Integer, Integer> kv : maxHeap) {
            res.add(kv.getKey());
        }
        return res;
    }
}
