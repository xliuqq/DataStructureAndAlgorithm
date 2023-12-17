package com.xliu.cs.algs.hash;

import com.xliu.cs.generate.ClassNote;

import java.util.*;

// 分布式一致性 Hash 算法，解决"单调性“问题，防止缓存节点新增/减少时的雪崩问题。
@ClassNote("一致性 Hash")
public class ConsistentHash {

    //虚拟节点
    private final int VIRTUAL_COPIES; // 物理节点至虚拟节点的复制倍数
    // 记录hash值对应的物理节点
    private final TreeMap<Integer, String> virtualNodes = new TreeMap<>(); // 哈希值 => 物理节点

    private final Hash hashAlg;

    // 根据物理节点，构建虚拟节点映射表
    public ConsistentHash(Hash hashAlg, Set<String> physicalNodes, int virutalReplicas) {
        this.hashAlg = hashAlg;
        this.VIRTUAL_COPIES = virutalReplicas;
        for (String nodeIp : physicalNodes) {
            addPhysicalNode(nodeIp);
        }
    }

    // 添加物理节点
    public void addPhysicalNode(String nodeIp) {
        for (int idx = 0; idx < VIRTUAL_COPIES; ++idx) {
            // 选择虚拟节点索引值
            int hash = hashAlg.hash(nodeIp + "#" + idx);
            // 如果索引值之前已经关联节点，则会被覆盖
            virtualNodes.put(hash, nodeIp);
        }
    }

    // 删除物理节点
    public void removePhysicalNode(String nodeIp) {
        for (int idx = 0; idx < VIRTUAL_COPIES; ++idx) {
            int hash = hashAlg.hash(nodeIp + "#" + idx);
            // 索引值对应该节点，则被删除
            if (nodeIp.equals(virtualNodes.get(hash))) {
                virtualNodes.remove(hash);
            }
        }
    }

    // 查找对象映射的节点
    public String getObjectNode(String object) {
        int hash = hashAlg.hash(object);
        // 寻找刚大于 hash 的节点，即对象属于该缓存节点
        SortedMap<Integer, String> tailMap = virtualNodes.tailMap(hash);
        int key = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
        return virtualNodes.get(key);
    }

    // 统计对象与节点的映射关系
    public void dumpObjectNodeMap(String label, int objectMin, int objectMax) {
        // 统计 IP => COUNT
        Map<String, Integer> objectNodeMap = new TreeMap<>();
        for (int object = objectMin; object <= objectMax; ++object) {
            String nodeIp = getObjectNode(Integer.toString(object));
            Integer count = objectNodeMap.get(nodeIp);
            objectNodeMap.put(nodeIp, (count == null ? 0 : count + 1));
        }

        // 打印
        double totalCount = objectMax - objectMin + 1;
        System.out.println("======== " + label + " ========");
        for (Map.Entry<String, Integer> entry : objectNodeMap.entrySet()) {
            double percent = 100.0 * entry.getValue() / totalCount;
            System.out.printf("IP=%s: RATE=%.1f%%\n", entry.getKey(), percent);
        }
    }

    public static void main(String[] args) {
        // 物理节点
        Set<String> physicalNodes = new TreeSet<>() {
            {
                add("192.168.1.101");
                add("192.168.1.102");
                add("192.168.1.103");
                add("192.168.1.104");
            }
        };

        List<Hash> hashes = new ArrayList<>(6) {
            {
                add(new Crc32Hash());
                add(new FNV1A_32_Hash());
                add(new FNV1_32_Hash());
                add(new MurMurHashV2());
                add(new KetamaHash());
                add(new JavaHash());
            }
        };

        // Crc32Hash 14.0s，分布均匀（5%）
        // FNV1A_32_Hash 11.2s，分布均匀（2%）
        // FNV1_32_Hash 3.7s，分布均匀（1%）
        // MurMurHashV2 13.1s，分布均匀（1%）
        // KetamaHash 15.6s，分布均匀（1%）
        // NativeHash 3.1s，分布不均匀
        for (Hash hash : hashes) {
            System.out.printf("%s benchmark:\n", hash.getClass().getName());
            long startTime = System.nanoTime();
            ConsistentHash ch = new ConsistentHash(hash, physicalNodes, 100000);

            // 初始情况，key 从 0 ~ 6553600
            ch.dumpObjectNodeMap("初始情况", 0, 6553600);

            // 删除物理节点
            ch.removePhysicalNode("192.168.1.103");
            ch.dumpObjectNodeMap("删除物理节点", 0, 6553600);

            // 添加物理节点
            ch.addPhysicalNode("192.168.1.108");
            ch.dumpObjectNodeMap("添加物理节点", 0, 6553600);

            long endTime = System.nanoTime();

            System.out.printf("Cost time: %f s\n", (endTime - startTime) / 1000000000.0);
        }

    }
}
