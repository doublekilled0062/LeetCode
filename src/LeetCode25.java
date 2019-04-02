/**
 * 25. k个一组翻转链表
 *
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class LeetCode25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1){
            //不反转
            return head;
        }
        int n = 1;
        ListNode pre = null;
        ListNode result = null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null){
            p2 = p2.next;
            n++;
            if(n % k == 0 && p2 != null){
                if(pre == null){
                    //第一组前节点是p1
                    pre = p1;
                }else {
                    pre.next = p2;
                    pre = p1;
                }
                if(result == null){
                    //头节点变成第一组的p2
                    result = p2;
                }
                //反转p1到p2之间的节点
                ListNode tmp = p2.next;
                ListNode tail = reverse(p1, p2);
                //最后p1更新 p2到循环开始继续
                p1 = tmp;
                p2 = tail;
                tail.next = tmp;
            }
        }
        if(result == null && n > 1){
            return head;
        }
        return result;
    }

    /**
     * 反转p1到p2之间的链表 不用考虑p2.next方法前已经保存
     * 返回尾节点
     * @param p1
     * @param p2
     * @return
     */
    private ListNode reverse(ListNode p1, ListNode p2){
        ListNode tail = p1;
        ListNode pre = p1;
        ListNode next = p1.next;
        while (next != null && next != p2){
            ListNode temp = next.next;
            next.next = pre;
            pre = next;
            next = temp;
        }
        if(next != null){
            ListNode tmp = next.next;
            next.next = pre;
            tail.next = tmp;
        }
        return tail;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.initTreeByLoop(new int[]{1,2,3,4,5});
        LeetCode25 leetCode25 = new LeetCode25();
        leetCode25.reverseKGroup(head, 2);
    }
}
