/**
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class LeetCode21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode head = result;
        while (l1!= null && l2 != null){
            if(l1.val <= l2.val){
                result.val = l1.val;
                l1 = l1.next;
            } else if(l1.val > l2.val){
                result.val = l2.val;
                l2 = l2.next;
            }
            if(l1 != null && l2 != null){
                ListNode next = new ListNode(0);
                result.next = next;
                result = result.next;
            }
        }
        if(l2 == null){
            result.next = l1;
        }
        if(l1 == null){
            result.next= l2;
        }
        return head;
    }

    /**
     * 这个省去了链表节点的复制过程
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode head = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }
        if (l1 != null) {
            result.next = l1;
        }
        if (l2 != null) {
            result.next = l2;
        }
        return head.next;
    }
}
