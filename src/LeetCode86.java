/**
 * 86. 分隔链表
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class LeetCode86 {
    /**
     * 这个题递归虽然好 但是效率会略差 因为需要重复遍历
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        if(head.val < x){
            head.next = partition(head.next, x);
            return head;
        }else {
            ListNode cur = head;
            while (cur.next != null && cur.next.val >= x){
                cur = cur.next;
            }
            if(cur.next == null){
                //没找到 直接返回head
                return head;
            }else {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = partition(head, x);
                return next;
            }
        }
    }

    /**
     * 要是能另外用一个链表就好说
     * @param head
     * @param x
     * @return
     */
    public ListNode partition2(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode smallNode = null;
        ListNode largeNode = null;
        ListNode resSmall = null;
        ListNode resLarge = null;

        while (head != null){
            if(head.val < x){
                if(smallNode == null){
                    smallNode = head;
                    resSmall = smallNode;
                }else {
                    smallNode.next = head;
                    smallNode = smallNode.next;
                }
            }else {
                if(largeNode == null){
                    largeNode = head;
                    resLarge = largeNode;
                }else {
                    largeNode.next = head;
                    largeNode = largeNode.next;
                }
            }
            head = head.next;
        }
        if(smallNode != null){
            smallNode.next = resLarge;
        }
        if(largeNode != null){
            largeNode.next = null;
        }
        return resSmall != null ? resSmall : resLarge;
    }

    public static void main(String[] args) {
        LeetCode86 leetCode86 = new LeetCode86();
        leetCode86.partition2(ListNode.initTreeByLoop(new int[]{1, 4, 3, 2, 5, 2}), 3);
    }
}
