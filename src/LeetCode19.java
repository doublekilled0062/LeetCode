/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class LeetCode19 {
    /**
     * 常规
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode result = head;
        if(head == null){
            return null;
        }
        while (n > 0){
            if(fast.next == null){
                //到头跳出
                break;
            }
            fast = fast.next;
            n--;
        }
        if(n == 1){
            //没走完，是删第一个
            return head.next;
        }
        if(n > 1){
            return null;
        }
        while(fast != null){
            fast = fast.next;
            if(fast == null){
                //找到了
                head.next = head.next.next;
                break;
            }else {
                head = head.next;
            }
        }
        return result;
    }

    /**
     * 追求效率
     * 保证n有效 其实好多的判定可以不用了 比如空头 n不够
     * 然后删除 可以走到删除的前一个 然后直接 node.next = node.next.next
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        while (n >= 0 && fast != null){
            fast = fast.next;
            n--;
        }
        if(fast == null && n >= 0){
            //判断是倒数第几正好是起点 还是正常走完
            return head.next;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        //不弄这个会让被删除的节点 有一个指针指向next
        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;
        return head;
    }
}
