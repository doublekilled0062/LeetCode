import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 146. LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例:
 *    LRUCache cache = new LRUCache(2) 2为缓存容量;
 *    cache.put(1, 1);
 *    cache.put(2, 2);
 *    cache.get(1);       // 返回  1
 *    cache.put(3, 3);    // 该操作会使得密钥 2 作废
 *    cache.get(2);       // 返回 -1 (未找到)
 *    cache.put(4, 4);    // 该操作会使得密钥 1 作废
 *    cache.get(1);       // 返回 -1 (未找到)
 *    cache.get(3);       // 返回  3
 *    cache.get(4);       // 返回  4
 */
public class LeetCode146 {
    /**
     * LRU缓存和LinkedHashMap一样
     * map+双端链表
     * 题目表示不清 如果get请求到已有数据，没有说这个数据是不是会更新成最新数据
     * 还要自己实现一个链表
     */
    class LRUCache {
        //map的size表示实际容量
        private Map<Integer, Node> map;
        private int capacity;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = null;
            tail = null;
            //大于负载因子不用重建hash表
            map = new HashMap<>(capacity * 2);
        }

        public int get(int key) {
            //先在map里找有没有
            if(!map.containsKey(key)){
                return -1;
            }
            //有的话 需要更新链表
            Node node = map.get(key);
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if(capacity == 0){
                return;
            }
            //先看有没有这个节点
            Node node = map.get(key);
            if(node != null){
                node.value = value;
            }else {
                node = new Node(key, value);
                //添加节点到头
                if(head == null){
                    head = node;
                    tail = node;
                }else {
                    node.next = head;
                    head.pre = node;
                    head = node;

                }
                map.put(key, node);
            }
            moveToHead(node);
        }

        private void moveToHead(Node node){
            //先看node是不是是头
            if(head != node){
                Node pre = node.pre;
                Node next = node.next;

                node.pre = null;
                node.next = head;
                head.pre = node;
                head = node;

                pre.next = next;
                if (next != null) {
                    next.pre = pre;
                } else {
                    tail = pre;
                }
            }
            if(map.size() > capacity){
                //去掉尾节点
                map.remove(tail.key);
                Node pre = tail.pre;
                tail.pre = null;
                pre.next = null;
                tail = pre;
            }
        }

        class Node{
            public int key;
            public int value;
            public Node pre;
            public Node next;
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        class LRUCache1 {
            private LinkedHashMap<Integer, Integer> map;
            private int capacity;

            public LRUCache1(int capacity) {
                map = new LinkedHashMap<>((int) (capacity / 0.75), 0.75f, true);
                this.capacity = capacity;
            }

            public int get(int key) {
                if (capacity == 0) {
                    return -1;
                }

                if (map.containsKey(key)) {
                    return map.get(key);
                }
                return -1;
            }

            public void put(int key, int value) {
                if (capacity == 0) {
                    return;
                }

                if (map.size() < capacity) {
                    map.put(key, value);
                } else {
                    if (!map.containsKey(key)) {
                        map.remove(map.keySet().iterator().next());
                    }
                    map.put(key, value);
                }
            }
        }
    }
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

    public static void main(String[] args) {
        LeetCode146 leetCode146 = new LeetCode146();
//        ["LRUCache","put","put","put","get","put","put","get","put","put","get","put","get","get","get","put","put","get","put","get"]
//        [[10],[7,28],[7,1],[8,15],[6],[10,27],[8,10],[8],[6,29],[1,9],[6],[10,7],[1],[2],[13],[8,30],[1,5],[1],[13,2],[12]]
        LeetCode146.LRUCache lruCache = leetCode146.new LRUCache(10);
        lruCache.put(7, 28);
        lruCache.put(7, 1);
        lruCache.put(8, 15);
        lruCache.get(6);
        lruCache.put(10, 27);
        lruCache.put(8, 10);
        lruCache.get(8);
        lruCache.put(6, 29);
        lruCache.put(1, 9);
        lruCache.get(6);
        lruCache.put(10, 7);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(13);
        lruCache.put(8, 30);
        lruCache.put(1, 5);
        lruCache.get(1);
        lruCache.put(13, 2);
        lruCache.get(12);

//        ["LRUCache","put","get","put","get","get"]
//        [[1],[2,1],[2],[3,2],[2],[3]]
//        LeetCode146.LRUCache lruCache = leetCode146.new LRUCache(1);
//        lruCache.put(2, 1);
//        lruCache.get(2);
//        lruCache.put(3, 2);
//        lruCache.get(2);
//        lruCache.get(3);

//        ["LRUCache","get","put","get","put","put","get","get"]
//        [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
//        LeetCode146.LRUCache lruCache = leetCode146.new LRUCache(2);
//        lruCache.get(2);
//        lruCache.put(2, 6);
//        lruCache.get(1);
//        lruCache.put(1, 5);
//        lruCache.put(1, 2);
//        lruCache.get(1);
//        System.out.println(lruCache.get(2));
    }
}
