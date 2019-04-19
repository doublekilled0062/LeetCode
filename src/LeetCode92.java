/**
 * 92. 反转链表 II
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class LeetCode92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n){
            return head;
        }

        //先找到要反转的节点
        ListNode cur = head;
        //前半部分不反转部分的最后一个 如果为null表示从头翻
        ListNode preLast = null;
        for(int i = 1; i < m; i++){
            preLast = cur;
            cur = cur.next;
        }
        //在要反转的节点之后 反转节点
        ListNode pre = cur;
        for(int i = 1; i <= n - m; i++){
            if(cur.next != null){
                ListNode tmp = cur.next.next;
                cur.next.next = pre;
                pre = cur.next;
                cur.next = tmp;
            }
        }
        if(preLast == null){
            return pre;
        }else {
            preLast.next = pre;
            return head;
        }
    }

    public static void main(String[] args) {
        LeetCode92 leetCode92 = new LeetCode92();
        leetCode92.reverseBetween(ListNode.initTreeByLoop(new int[]{1,2,3,4,5}), 3, 4);
    }
}
