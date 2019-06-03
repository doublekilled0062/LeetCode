import java.util.List;
import java.util.Stack;

/**
 * 143. 重排链表
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class LeetCode143 {
    /**
     * 不借助栈就得用平方的复杂度了
     * 760ms 5%
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode tail = head;
        while (tail.next.next != null){
            tail = tail.next;
        }
        ListNode next = head.next;
        ListNode last = tail.next;
        head.next = last;
        last.next = next;
        tail.next = null;
        reorderList(next);
    }

    /**
     * 借助栈试一下
     * @param head
     */
    public void reorderList2(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null){
            stack.push(node);
            node = node.next;
        }
        ListNode first = head;
        ListNode second = stack.pop();
        while (first != second && first.next != second){
            ListNode next = first.next;
            first.next = second;
            second.next = next;
            first = next;
            second = stack.pop();
        }
        if(first == second){
            first.next = null;
        }else if(first.next == second){
            second.next = null;
        }
        return;
    }

    /**
     * 跟据栈的思路 如果不用栈
     * 应该是先找中间 然后反转后半部分 然后重排
     * 找中间用双指针
     * @param head
     */
    public void reorderList3(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode mid = head;
        ListNode tail = head;
        while (tail.next != null && tail.next.next != null){
            mid = mid.next;
            tail = tail.next.next;
        }
        //反转mid.next作为节点的链表
        ListNode reverse = reverse(mid.next);
        //组合新链表
        ListNode first = head;
        ListNode second = reverse;
        ListNode last = null;
        while (second != null){
            ListNode fnext = first.next;
            ListNode snext = second.next;
            first.next = second;
            second.next = fnext;
            last = second;
            first = fnext;
            second = snext;
        }
        if(first == mid){
            first.next = null;
        }else {
            last.next = null;
        }
        return;
    }

    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode pre = head.next;
        while (pre != null){
            ListNode next = pre.next;
            pre.next = cur;
            cur = pre;
            pre = next;
        }
        head.next = null;
        return cur;
    }

    public static void main(String[] args) {
        LeetCode143 leetCode143 = new LeetCode143();
        leetCode143.reorderList3(ListNode.initTreeByLoop(new int[]{1, 2, 3, 4}));
        leetCode143.reorderList3(ListNode.initTreeByLoop(new int[]{1, 2, 3, 4, 5}));
    }
}
