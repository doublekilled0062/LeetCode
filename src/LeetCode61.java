/**
 * 61. 旋转链表
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class LeetCode61 {
    /**
     * 先求链表长度找到尾指针
     * 然后再看k mod len 按求倒数节点
     * 再反转指向
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode tail = head;
        int len = 1;
        while (tail.next != null){
            len++;
            tail = tail.next;
        }
        k = k % len;
        if(k == 0){
            return head;
        }
        //求倒数第k + 1个节点
        ListNode fast = head;
        ListNode slow = head;
        //先走k步
        while (k > 0){
            fast = fast.next;
            k--;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        ListNode res = slow.next;
        tail.next = head;
        slow.next = null;
        return res;
    }
}
