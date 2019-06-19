/**
 * 147. 对链表进行插入排序
 *
 * 对链表进行插入排序。
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class LeetCode147 {
    /**
     * 就是插入排序的原理
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null){
            if(cur.val >= pre.val){
                //待排序节点大于等于已经排好序的最后一个节点 不用排了 直接挪pre和cur
                cur = cur.next;
                pre = pre.next;
                continue;
            }else {
                //需要删除这个节点 找一个合适位置 先保存cur的下一个节点 pre节点不变 指向next就好
                ListNode next = cur.next;
                pre.next = next;
                //先判断是不是直接放到头节点
                if(cur.val <= head.val){
                    cur.next = head;
                    head = cur;
                }else {
                    //cur插入到res和pre之间的一个地方
                    ListNode tmp = head;
                    while (tmp != pre){
                        if(tmp.next.val <= cur.val){
                            tmp = tmp.next;
                            continue;
                        }
                        //找到位置了
                        ListNode tmpNext = tmp.next;
                        tmp.next = cur;
                        cur.next = tmpNext;
                        break;
                    }
                }
                //cur变成next 继续迭代
                cur = next;
            }
        }
        return head;
    }

    /**
     * 优化下代码结构
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if(cur.val >= pre.val){
                //不用从头插入了
                pre = cur;
            }else {
                //先处理pre
                pre.next = cur.next;
                //给cur找位置 cur介于p1和p2之间
                ListNode p1 = null;
                ListNode p2 = head;
                while (cur.val > p2.val){
                    p1 = p2;
                    p2 = p2.next;
                }
                cur.next = p2;
                if(p1 == null){
                    //cur是第一个
                    head = cur;
                }else {
                    p1.next = cur;
                }
            }
            //cur的下一个位置
            cur = pre.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LeetCode147 leetCode147 = new LeetCode147();
        leetCode147.insertionSortList2(ListNode.initTreeByLoop(new int[]{4, 2, 1, 3}));
    }
}
