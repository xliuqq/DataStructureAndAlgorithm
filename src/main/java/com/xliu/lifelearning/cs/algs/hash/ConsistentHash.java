package com.xliu.lifelearning.cs.algs.hash;

import java.util.*;

// TODO:
//  https://github.com/evasnowind/distributed-dev-learning/tree/master/consistent-hash
// https://github.com/Yikun/hashes
public class ConsistentHash {

    //虚拟节点
    private final int VIRTUAL_COPIES; // 物理节点至虚拟节点的复制倍数
    private TreeMap<Integer, String> virtualNodes = new TreeMap<>(); // 哈希值 => 物理节点

    private Hash hashAlg;

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
            int hash = hashAlg.hash(nodeIp + "#" + idx);
            virtualNodes.put(hash, nodeIp);
        }
    }

    // 删除物理节点
    public void removePhysicalNode(String nodeIp) {
        for (int idx = 0; idx < VIRTUAL_COPIES; ++idx) {
            int hash = hashAlg.hash(nodeIp + "#" + idx);
            virtualNodes.remove(hash);
        }
    }

    // 查找对象映射的节点
    public String getObjectNode(String object) {
        int hash = hashAlg.hash(object);
        // 所有大于 hash 的节点
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
            long percent = (int) (100 * entry.getValue() / totalCount);
            System.out.println("IP=" + entry.getKey() + ": RATE=" + percent + "%");
        }
    }

    public static void main(String[] args) {
        // 物理节点
        Set<String> physicalNodes = new TreeSet<String>() {
            {
                add("192.168.1.101");
                add("192.168.1.102");
                add("192.168.1.103");
                add("192.168.1.104");
            }
        };
        {
            // KetamaHash 19.9s
            // FNV1_32_Hash 6.3s
            // FNV1A_32_Hash 13.2s
            // MurMurHashV2 14.4s
            // Crc32Hash 15.0s
            long startTime = System.nanoTime();
            ConsistentHash ch = new ConsistentHash(new Crc32Hash(), physicalNodes, 100000);

            // 初始情况
            ch.dumpObjectNodeMap("初始情况", 0, 6553600);

            // 删除物理节点
            ch.removePhysicalNode("192.168.1.103");
            ch.dumpObjectNodeMap("删除物理节点", 0, 6553600);

            // 添加物理节点
            ch.addPhysicalNode("192.168.1.108");
            ch.dumpObjectNodeMap("添加物理节点", 0, 6553600);

            long endTime = System.nanoTime();

            System.out.printf("Cost time: %f s", (endTime - startTime) / 1000000000.0);
        }

    }
}
