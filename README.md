# 数据结构与算法


## 算法
> 详细内容可见 [知识空间](https://xliuqq.github.io/blog_md/code_guide/algs/common.html)

### 位运算


[位图排序](src/main/java/org/xliu/cs/algs_ds/algs/bit/BitMapSort.java)

[计算数字中 1 bit 的个数](src/main/java/org/xliu/cs/algs_ds/algs/bit/CountBits.java)

- 右移法，循环次数等于最高位1的位置

- 减一相与法，循环次数等于1出现的次数

### 减而治之


[二分搜索及其变种](src/main/java/org/xliu/cs/algs_ds/algs/divideconquer/BinarySearch.java)

- 搜索等于 value 值的元素中最大的下标，没有相等元素，则返回 -1

- 搜索 < value 值的元素中最大的下标，没有比它小的则返回 -1

- 搜索等于 value 值的元素中最小的下标，没有相等元素，则返回 -1

- 搜索 > value 值的元素中最小的下标，没有比它大的则返回 -1

- 搜索等于value的下标，如果不存在，则返回插入该值的（下标+1）的负数

[数组里的最大的K个数（无序）](src/main/java/org/xliu/cs/algs_ds/algs/divideconquer/KMaxElement.java)

### 动态规划


[最长增长子序列](src/main/java/org/xliu/cs/algs_ds/algs/dp/LongestIncreasingSubSequence.java)

### 哈希（hash）


[一致性 Hash](src/main/java/org/xliu/cs/algs_ds/algs/hash/ConsistentHash.java)

[Crc32Hash](src/main/java/org/xliu/cs/algs_ds/algs/hash/Crc32Hash.java)

[FNV1A_32_Hash](src/main/java/org/xliu/cs/algs_ds/algs/hash/FNV1A_32_Hash.java)

[FNV1_32_Hash](src/main/java/org/xliu/cs/algs_ds/algs/hash/FNV1_32_Hash.java)

[JavaHash](src/main/java/org/xliu/cs/algs_ds/algs/hash/JavaHash.java)

[KetamaHash](src/main/java/org/xliu/cs/algs_ds/algs/hash/KetamaHash.java)

[MurMurHashV2](src/main/java/org/xliu/cs/algs_ds/algs/hash/MurMurHashV2.java)

### 数学（math）


[斐波那契数列（矩阵方式）](src/main/java/org/xliu/cs/algs_ds/algs/math/Fibonacci.java)

[N个小矩阵构成大矩阵](src/main/java/org/xliu/cs/algs_ds/algs/math/PerfectRectangle.java)

[完美洗牌](src/main/java/org/xliu/cs/algs_ds/algs/math/PerfectShuffle.java)

[点在三角形内](src/main/java/org/xliu/cs/algs_ds/algs/math/PointInTriangle.java)

- 通过叉积判断

- 通过面积判断

[均匀洗牌](src/main/java/org/xliu/cs/algs_ds/algs/math/Shuffle.java)

[平方根计算方法](src/main/java/org/xliu/cs/algs_ds/algs/math/Sqrt.java)

- 平方根倒数速算法

- 牛顿公式计算平方根

### 递归


[N个字母的所有的排列组合](src/main/java/org/xliu/cs/algs_ds/algs/recursive/Permutation.java)

[递归变循环通用方法](src/main/java/org/xliu/cs/algs_ds/algs/recursive/RecursiveToFor.java)

- 汉诺塔非递归实现

### 搜索（search）


[24点可行解搜索](src/main/java/org/xliu/cs/algs_ds/algs/search/Card24Points.java)

- 动态规划解决24点的问题

[1-n的乱序数组前缀反转使其有序的最大次数](src/main/java/org/xliu/cs/algs_ds/algs/search/PrefixReverseSort.java)

### 树（tree）


[二叉树叶节点间最长距离和树高度](src/main/java/org/xliu/cs/algs_ds/algs/tree/MaxPathBetweenLeaf.java)

[根据遍历序恢复树](src/main/java/org/xliu/cs/algs_ds/algs/tree/RebuildTree.java)

- 通过前序遍历和中序遍历构造树

[二叉树遍历](src/main/java/org/xliu/cs/algs_ds/algs/tree/TraverseTree.java)

- 中序遍历（非递归）

- 中序遍历（递归）

- 层次遍历

- 后序遍历（非递归）

- 后序遍历（递归）

- 前序遍历（非递归）

- 前序遍历（递归）

## 性能测试


[位图排序跟系统排序性能](src/main/java/org/xliu/cs/algs_ds/benchmark/BitSortPerformance.java)

[文件追加写性能](src/main/java/org/xliu/cs/algs_ds/benchmark/FileAppendPerformance.java)

## 数据结构
> 详细内容可见 [知识空间](https://xliuqq.github.io/blog_md/code_guide/data_structure/array.html)

### 数组


[最小堆](src/main/java/org/xliu/cs/algs_ds/ds/array/Heap.java)

[树状数组](src/main/java/org/xliu/cs/algs_ds/ds/array/TreeArray.java)

[并查集](src/main/java/org/xliu/cs/algs_ds/ds/array/UnionSet.java)

### 图


[数组加链表表示图](src/main/java/org/xliu/cs/algs_ds/ds/graph/ListGraph.java)

[矩阵表示图（稠密图）](src/main/java/org/xliu/cs/algs_ds/ds/graph/MatrixGraph.java)

### 索引


[布隆过滤器](src/main/java/org/xliu/cs/algs_ds/ds/index/BloomFilter.java)

### 链表


[单向链表](src/main/java/org/xliu/cs/algs_ds/ds/linkedlist/SingleLinkedList.java)

### 树


[二叉树](src/main/java/org/xliu/cs/algs_ds/ds/tree/BinaryTreeNode.java)

[线段树](src/main/java/org/xliu/cs/algs_ds/ds/tree/SegmentTree.java)

## 刷题库


### 位运算


[出现次数不一样的数](src/main/java/org/xliu/cs/algs_ds/leetcode/bit/SingleNumber.java)

- 只有一个元素出现一次，其它都出现三次

- 只有一个元素出现一次，其它都出现两次

- 只有两个元素出现一次，其它都出现两次

### 减而治之


[数组里的大多数元素](src/main/java/org/xliu/cs/algs_ds/leetcode/divideconquer/MajorityElement.java)

- 超过1/2的元素

- 超过1/3的元素

### 动态规划


[暗黑字符串](src/main/java/org/xliu/cs/algs_ds/leetcode/dp/BlackString.java)

### 图


[欧拉图+并查集：单词列表首尾相连](src/main/java/org/xliu/cs/algs_ds/leetcode/graph/WordHeadTail.java)

### 贪心算法


[变成回文序列](src/main/java/org/xliu/cs/algs_ds/leetcode/greedy/SumHuiWen.java)

### 堆排序


[频率最高的K个元素](src/main/java/org/xliu/cs/algs_ds/leetcode/heap/TopKFrequent.java)

