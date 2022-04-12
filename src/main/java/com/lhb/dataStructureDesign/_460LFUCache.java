package com.lhb.dataStructureDesign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 146. LRU缓存机制（中等）
 * @author: linhaibin
 * @create: 2022-04-12 10:43
 **/
public class _460LFUCache {

    /**
     * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
     * <p>
     * 实现 LFUCache 类：
     * <p>
     * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
     * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
     * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
     * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
     * <p>
     * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
     * <p>
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：
     * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
     * 输出：
     * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
     * <p>
     * 解释：
     * // cnt(x) = 键 x 的使用计数
     * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
     * LFUCache lfu = new LFUCache(2);
     * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
     * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
     * lfu.get(1);      // 返回 1
     * // cache=[1,2], cnt(2)=1, cnt(1)=2
     * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
     * // cache=[3,1], cnt(3)=1, cnt(1)=2
     * lfu.get(2);      // 返回 -1（未找到）
     * lfu.get(3);      // 返回 3
     * // cache=[3,1], cnt(3)=2, cnt(1)=2
     * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
     * // cache=[4,3], cnt(4)=1, cnt(3)=2
     * lfu.get(1);      // 返回 -1（未找到）
     * lfu.get(3);      // 返回 3
     * // cache=[3,4], cnt(4)=1, cnt(3)=3
     * lfu.get(4);      // 返回 4
     * // cache=[3,4], cnt(4)=2, cnt(3)=3
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= capacity <= 104
     * 0 <= key <= 105
     * 0 <= value <= 109
     * 最多调用 2 * 105 次 get 和 put 方法
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lfu-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.print(lfu.get(1) + " ");      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.print(lfu.get(2) + " ");      // 返回 -1（未找到）
        System.out.print(lfu.get(3) + " ");      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.print(lfu.get(1) + " ");      // 返回 -1（未找到）
        System.out.print(lfu.get(3) + " ");      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4) + " ");      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
        System.out.println(JSON.toJSONString(lfu, SerializerFeature.WriteMapNullValue));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class LFUCache {
        // key 到 val 的映射，我们称为 KV 表
        private Map<Integer, Integer> keyToValue;
        // key 到 freq 的映射，我们称为 KF 表
        private Map<Integer, Integer> keyToFreq;
        // freq 到 key 列表的映射，我们称为 FK 表
        private Map<Integer, LinkedHashSet<Integer>> freqToKeys;

        // 记录 LFU 缓存的最大容量
        private int capacity;
        // 记录最小的频次
        private int minFreq;

        public LFUCache(int capacity) {
            this.keyToValue = new HashMap<>();
            this.keyToFreq = new HashMap<>();
            this.freqToKeys = new HashMap<>();
            this.capacity = capacity;
            this.minFreq = 0;
        }

        public int get(int key) {
            if (keyToValue.containsKey(key)) {
                // 增加 key 对应的 freq
                increaseFreq(key);
                return keyToValue.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (this.capacity <= 0) return;
            /* 若 key 已存在，修改对应的 val 即可 */
            if (keyToValue.containsKey(key)) {
                keyToValue.put(key, value);
                // key 对应的 freq 加一
                increaseFreq(key);
                return;
            }
            /* key 不存在，需要插入 */
            /* 容量已满的话需要淘汰一个 freq 最小的 key */
            if (keyToValue.size() >= capacity) {
                removeMinFreq();
            }
            /* 插入 key 和 val，对应的 freq 为 1 */
            // 插入 KV 表
            keyToValue.put(key, value);
            // 插入 KF 表
            keyToFreq.put(key, 1);
            // 插入 FK 表
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            // 插入新 key 后最小的 freq 肯定是 1
            freqToKeys.get(1).add(key);
            minFreq = 1;

        }

        private void removeMinFreq() {
            // freq 最小的 key 列表
            LinkedHashSet<Integer> linkedHashSet = freqToKeys.get(this.minFreq);
            // 其中最先被插入的那个 key 就是该被淘汰的 key
            Integer deleteKey = linkedHashSet.iterator().next();
            /* 更新 FK 表 */
            linkedHashSet.remove(deleteKey);
            if (linkedHashSet.isEmpty()) {
                freqToKeys.remove(minFreq);
//                minFreq++;
                // 问：这里需要更新 minFreq 的值吗？
            }
            /* 更新 KV 表 */
            keyToValue.remove(deleteKey);
            /* 更新 KF 表 */
            keyToFreq.remove(deleteKey);
        }

        public void increaseFreq(int key) {
            Integer freq = keyToFreq.get(key);
            /* 更新 KF 表 */
            keyToFreq.put(key, freq + 1);

            /* 更新 FK 表 */

            freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
            // 将 key 加入 freq + 1 对应的列表中
            freqToKeys.get(freq + 1).add(key);

            // 将 key 从 freq 对应的列表中删除
            LinkedHashSet oldSet = freqToKeys.get(freq);
            oldSet.remove(key);
            // 如果 freq 对应的列表空了，移除这个 freq
            if (oldSet.isEmpty()) {
                freqToKeys.remove(freq);
                // 如果这个 freq 恰好是 minFreq，更新 minFreq
                if (freq == this.minFreq) {
                    this.minFreq++;
                }
            }


        }
    }


    /**
     * 从LeetCode效率答案模板拷过来的
     */
    class LFUCache1 {

        private int minFreq;
        private final int capacity;
        HashMap<Integer, Node> keyTable; // 节点哈希表，存储(key, Node)
        HashMap<Integer, DoubleList> freqTable; // 频率哈希表，存储(freq, DoubleList)

        public LFUCache1() {
            this(5);
        }

        public LFUCache1(int capacity) {

            this.minFreq = 0;
            this.capacity = capacity;
            this.keyTable = new HashMap<>();
            this.freqTable = new HashMap<>();
        }

        public int get(int key) {
            if (capacity == 0)
                return -1;

            if (!keyTable.containsKey(key))
                return -1;

            // 获取该key对应的node
            Node node = keyTable.get(key);
            // 将该节点从旧频率链表删除，创建一个新的节点（频率加1），将其加入到新的频率链表
            updateFreq(node);

            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0)
                return;
            Node oldNode = keyTable.get(key);
            if (oldNode != null) { // 已经存在对应的key，修改其值为value
                // 修改值
                oldNode.value = value;
                // 更新频率
                updateFreq(oldNode);
            } else {
                // 若已经达到容量上限，移除节点
                if (capacity <= keyTable.size()) {
                    // 获取频率最小的链表
                    DoubleList minFreqList = freqTable.get(minFreq);
                    // 获取要移除的节点
                    Node removeNode = minFreqList.getFirst();
                    // 节点表中移除对应的节点
                    keyTable.remove(removeNode.key);
                    // 链表中移除节点
                    minFreqList.removeFirst();
                    if (minFreqList.size() == 0) {
                        freqTable.remove(minFreq);
                        minFreq++;
                    }

                }
                Node newNode = new Node(key, value, 1); // 创建一个对应key-value的频率为1的节点
                DoubleList list = freqTable.get(1); // 获取频率为1的链表
                if (list == null) {
                    // 创建新的，并加到频率哈希表中
                    list = new DoubleList();
                    freqTable.put(1, list);
                    // 更新最小频率！
                    minFreq = 1;
                }
                // 添加到对应频率链表的末尾
                list.addLast(newNode);
                // 同时，节点哈希表也要更新
                keyTable.put(newNode.key, newNode);
            }
        }

        private void updateFreq(Node node) {
            // 获取它之前所在的频率链表，并从中移除该节点
            DoubleList beforeList = freqTable.get(node.freq);
            if (beforeList == null)
                return;
            beforeList.remove(node);
            //移除节点之后，判断链表是否为空
            if (beforeList.size() == 0) {
                // 若链表为空，要在频率哈希表中删除该链表
                freqTable.remove(node.freq);
                // 移除链表之后，缓存中最小频率就变了
                if (minFreq == node.freq) {
                    // 因为当前节点（它之前就是最小频率）被访问了一次，故频率会加一。如果之前只有这一个节点，那现在最小频率还是这个节点
                    minFreq += 1;
                }
            }
            // 获取频率为freq+1的链表
            DoubleList curList = freqTable.get(node.freq + 1);
            if (curList == null) { // 如果还不存在对应频率的链表
                // 创建新的，并加到频率哈希表中
                curList = new DoubleList();
                freqTable.put(node.freq + 1, curList);
            }
            // 创建新节点
            Node newNode = new Node(node.key, node.value, node.freq + 1);
            // 添加到对应频率链表的末尾
            curList.addLast(newNode);
            // 同时，节点哈希表也要更新
            keyTable.put(node.key, newNode);
        }
    }

    class DoubleList {
        private final Node head; // 头节点，用作标识链表头，并不是实际的存储节点
        private final Node tail; // 尾节点，用作标识链表尾，并不是实际的存储节点
        private int size; // 链表大小

        public DoubleList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 在尾部添加节点
         *
         * @param node 待添加的节点
         */
        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        /**
         * 移除指定节点
         *
         * @param node 待移除的节点
         */
        public void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }

        /**
         * 移除第一个节点，即最久未使用的节点
         *
         * @return 第一个节点
         */
        public Node removeFirst() {
            if (head.next == null)
                return null;

            Node first = head.next;
            remove(first);

            return first;
        }

        /**
         * 获取第一个节点
         *
         * @return 第一个节点
         */
        public Node getFirst() {
            return head.next;
        }

        /**
         * 获取最后一个节点
         *
         * @return 最后一个节点
         */
        public Node getLast() {
            return tail.prev;
        }

        /**
         * 获取链表大小
         *
         * @return 链表大小
         */
        public int size() {
            return this.size;
        }
    }

    class Node {
        int key, value; // 键-值
        int freq; // 访问频次
        Node prev, next; // 前驱后继指针


        public Node() {
            this(0, 0, 0);
        }

        public Node(int key, int value) {
            this(key, value, 1);
        }

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}
