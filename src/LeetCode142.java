/**
 * 142. 环形链表 II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *     3 --> 2 --> 0 --> 4
 *           |           |
 *           <------------
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *      1 --> 2
 *      |     |
 *      <------
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *      1 --> null
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * 在真实的面试中遇到过这道题？
 */
public class LeetCode142 {
    /**
     * 相遇以后快针从head继续走 如果能遇上
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pSlow = head;
        ListNode pFast = head;
        while (pSlow != null && pFast != null){
            if(pSlow.next == null || pFast.next == null || pFast.next.next == null){
                return null;
            }
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            if(pSlow == pFast){
                //相遇以后 fast一步一步走 遇到慢针就是起点
                pFast = head;
                while (pFast != pSlow) {
                    pFast = pFast.next;
                    pSlow = pSlow.next;
                }
                return pFast;
            }
        }
        return null;
    }
}
