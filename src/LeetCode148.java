/**
 * 148. 排序链表
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class LeetCode148 {
    /**
     * 一开始写的以为是快排思想后来想了下这个其实是个O(n^2)的解法
     * 快排指针是两头走的 这个充其量只能算递归插入
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = sortList(head, null);
        return newHead;
    }

    /**
     * 快排思想
     * @param head
     * @param tail
     * @return
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        if(head.next == tail){
            return head;
        }
        //处理cur.next这样不用保存下一个指针
        ListNode cur = head;
        ListNode newHead = head;
        while (cur.next != tail){
            if(cur.next.val >= head.val){
                cur = cur.next;
            }else {
                ListNode next = cur.next.next;
                cur.next.next = newHead;
                newHead = cur.next;
                cur.next = next;
            }
        }
        if(newHead == head){
            head.next = sortList(head.next, tail);
            return newHead;
        }else if(head.next == tail){
            return sortList(newHead, head);
        }else {
            head.next = sortList(head.next, tail);
            return sortList(newHead, head);
        }
    }

    /**
     * 双指针找重点 然后归并就好了
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode mid = head;
        while (fast != null && fast.next != null) {
            mid = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        //从中间断开链表 防止递归时出现问题
        mid.next = null;
        ListNode left = sortList2(head);
        ListNode right = sortList2(slow);
        return merge(left, right);
    }

    /**
     * 合并两个有序链表 leetcode21
     * @param l1
     * @param l2
     * @return
     */
    private ListNode merge(ListNode l1, ListNode l2) {
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

    public static void main(String[] args) {
        LeetCode148 leetCode148 = new LeetCode148();
        leetCode148.sortList(ListNode.initTreeByLoop(new int[]{4, 2, 1, 3, 5, 8, 9}));
    }
}
