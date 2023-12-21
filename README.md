# 数据结构与算法

## 算法
> 详细内容可见 [知识空间](https://xliuqq.github.io/blog_md/code_guide/algs/common.html)

### 减而治之


[数组里的最大的K个数（无序）](src/main/java/com/xliu/cs/algs/divideconquer/KMaxElement.java)

### 哈希（hash）


[一致性 Hash](src/main/java/com/xliu/cs/algs/hash/ConsistentHash.java)

[Crc32Hash](src/main/java/com/xliu/cs/algs/hash/Crc32Hash.java)

[FNV1A_32_Hash](src/main/java/com/xliu/cs/algs/hash/FNV1A_32_Hash.java)

[FNV1_32_Hash](src/main/java/com/xliu/cs/algs/hash/FNV1_32_Hash.java)

[JavaHash](src/main/java/com/xliu/cs/algs/hash/JavaHash.java)

[KetamaHash](src/main/java/com/xliu/cs/algs/hash/KetamaHash.java)

[MurMurHashV2](src/main/java/com/xliu/cs/algs/hash/MurMurHashV2.java)

### 数学（math）


[斐波那契数列（矩阵方式）](src/main/java/com/xliu/cs/algs/math/Fibonacci.java)

[N个小矩阵构成大矩阵](src/main/java/com/xliu/cs/algs/math/PerfectRectangle.java)

[完美洗牌](src/main/java/com/xliu/cs/algs/math/PerfectShuffle.java)

[点在三角形内](src/main/java/com/xliu/cs/algs/math/PointInTriangle.java)

- 通过叉积判断

- 通过面积判断

[均匀洗牌](src/main/java/com/xliu/cs/algs/math/Shuffle.java)

[平方根计算方法](src/main/java/com/xliu/cs/algs/math/Sqrt.java)

- 牛顿公式计算平方根

- 平方根倒数速算法

### 递归


[递归变循环通用方法](src/main/java/com/xliu/cs/algs/recursive/RecursiveToFor.java)

- 汉诺塔非递归实现

### 搜索（search）


[二分搜索及其变种](src/main/java/com/xliu/cs/algs/search/BinarySearch.java)

- 搜索等于value的下标，如果不存在，则返回插入该值的（下标+1）的负数

- 搜索等于 value 值的元素中最小的下标，没有相等元素，则返回 -1

- 搜索 < value 值的元素中最大的下标，没有比它小的则返回 -1

- 搜索 > value 值的元素中最小的下标，没有比它大的则返回 -1

- 搜索等于 value 值的元素中最大的下标，没有相等元素，则返回 -1

### 树（tree）


[二叉树叶节点间最长距离和树高度](src/main/java/com/xliu/cs/algs/tree/MaxPathBetweenLeaf.java)

[根据遍历序恢复树](src/main/java/com/xliu/cs/algs/tree/RebuildTree.java)

- 通过前序遍历和中序遍历构造树

[二叉树遍历](src/main/java/com/xliu/cs/algs/tree/TraverseTree.java)

- 中序遍历（递归）

- 中序遍历（非递归）

- 层次遍历

- 前序遍历（非递归）

- 前序遍历（递归）

- 后序遍历（递归）

- 后序遍历（非递归）

## 性能测试


[文件追加写性能](src/main/java/com/xliu/cs/benchmark/FileAppendPerformance.java)

## 数据结构
> 详细内容可见 [知识空间](https://xliuqq.github.io/blog_md/code_guide/data_structure/array.html)

### 数组


[最小堆](src/main/java/com/xliu/cs/ds/array/Heap.java)

[树状数组](src/main/java/com/xliu/cs/ds/array/TreeArray.java)

[并查集](src/main/java/com/xliu/cs/ds/array/UnionSet.java)

### 图


[数组加链表表示图](src/main/java/com/xliu/cs/ds/graph/ListGraph.java)

[矩阵表示图（稠密图）](src/main/java/com/xliu/cs/ds/graph/MatrixGraph.java)

### 索引


[布隆过滤器](src/main/java/com/xliu/cs/ds/index/BloomFilter.java)

### 链表


[单向链表](src/main/java/com/xliu/cs/ds/linkedlist/SingleLinkedList.java)

### 树


[二叉树](src/main/java/com/xliu/cs/ds/tree/BinaryTreeNode.java)

[线段树](src/main/java/com/xliu/cs/ds/tree/SegmentTree.java)

## 刷题库


### 减而治之


[数组里的大多数元素](src/main/java/com/xliu/cs/leetcode/divideconquer/MajorityElement.java)

- 超过1/2的元素

- 超过1/3的元素

### 贪心算法


[变成回文序列](src/main/java/com/xliu/cs/leetcode/greedy/SumHuiWen.java)

### 堆排序


[频率最高的K个元素](src/main/java/com/xliu/cs/leetcode/heap/TopKFrequent.java)

