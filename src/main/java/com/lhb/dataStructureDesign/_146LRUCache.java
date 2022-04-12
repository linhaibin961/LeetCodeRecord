package com.lhb.dataStructureDesign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: LeetCodeRecord
 * @description: 146. LRU缓存机制（中等）
 * @author: linhaibin
 * @create: 2022-04-12 10:43
 **/
public class _146LRUCache {

    /**
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * <p>
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= capacity <= 3000
     * 0 <= key <= 10000
     * 0 <= value <= 105
     * 最多调用 2 * 105 次 get 和 put
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lru-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.print(lRUCache.get(2) + " ");    // 返回 1
        lRUCache.put(1, 1); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.print(lRUCache.get(2) + " ");    // 返回 -1 (未找到)
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.print(lRUCache.get(2) + " ");    // 返回 -1 (未找到)
//        System.out.print(lRUCache.get(3) + " ");    // 返回 3
//        System.out.println(lRUCache.get(4) + " ");    // 返回 4
        System.out.println(JSON.toJSONString(lRUCache, SerializerFeature.WriteMapNullValue));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class LRUCache1 {
        private int capacity;
        private LinkedHashMap<Integer, Integer> listMap = new LinkedHashMap<>();

        public LRUCache1(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (listMap.containsKey(key)) {
                makeRecently(key);
                return listMap.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (listMap.containsKey(key)) {
                listMap.put(key, value);
                makeRecently(key);
                return;
            }
            if (listMap.size() >= capacity) {
                Integer first = listMap.keySet().iterator().next();
                listMap.remove(first);
            }
            listMap.put(key, value);

        }

        public void makeRecently(int key) {
            Integer value = listMap.get(key);
            listMap.remove(key);
            listMap.put(key, value);
        }
    }

    /**
     * 手动实现
     */
    @Data
    static class LRUCache {
        private int capacity;
        private Map<Integer, Node> map = new HashMap<>();
        private DoubleList cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new DoubleList();
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                makeRecently(key);
                return map.get(key).value;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (map.containsKey(key)) {
                // put进去新值，返回老的值
                cache.remove(map.put(key, node));
                cache.addLast(node);

//                Node temp = map.get(key);
//                temp.value =value;
//                map.put(key, temp);
//                makeRecently(key);
                return;
            }
            if (cache.size >= capacity) {
                Node next = cache.head.next;
                // next为tail时判断一下
                if (cache.remove(next)) {
                    map.remove(next.key);
                }
            }
            map.put(key, node);
            cache.addLast(node);
        }

        public void makeRecently(int key) {
            Node node = map.get(key);
            cache.remove(node);
            cache.addLast(node);

        }

        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //        @Data
        class DoubleList {
            Node head;
            Node tail;
            int size = 0;

            public DoubleList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
            }

            public void addLast(Node node) {
                tail.prev.next = node;
                node.prev = tail.prev;
                node.next = tail;
                tail.prev = node;
                size++;
            }

            public boolean remove(Node node) {
                if (node != tail) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    size--;
                    return true;
                }
                return false;
            }


        }
    }

}
