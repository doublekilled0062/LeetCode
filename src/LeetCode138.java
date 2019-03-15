/**
 * 138. 复制带随机指针的链表
 *
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *
 * 提示：
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 */
public class LeetCode138 {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    public Node copyRandomList(Node head) {
        //判断异常返回
        if(head == null){
            return null;
        }
        //先复制这个链表
        Node p = head;
        while (p != null){
            Node copy = new Node(p.val, null, null);
            copy.next = p.next;
            p.next = copy;
            p = p.next.next;
        }

        //复制random 必须先复制再断开 要不然往前便利时random的指针如果指向的是前面已遍历过的节点就会出问题
        p = head;
        while (p != null){
            if(p.random == null){
                p.next.random = null;
            }else {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        //断开链表
        p = head;
        Node newHead = head.next;
        Node pcopy = newHead;
        while (p != null){
            p.next = p.next.next;
            if(pcopy.next != null){
                pcopy.next = pcopy.next.next;
            }else {
                pcopy.next = null;
            }
            p = p.next;
            if(p == null){
                pcopy = null;
            }else {
                pcopy = p.next;
            }
        }
        return newHead;
    }
}
