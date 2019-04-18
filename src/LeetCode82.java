/**
 * 82. 删除排序链表中的重复元素 II
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class LeetCode82 {
    /**
     * 传统循环类要处理各种异常边界
     * 各种逻辑判断是为了释放被删除的节点的指针
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode res = null;
        while (cur.next != null){
            if(cur.val == cur.next.val){
                if(cur.next.next != null){
                    //后面有节点 cur不动
                    if(cur.next.val == cur.next.next.val){
                        //删除cur.next
                        ListNode tmp = cur.next.next;
                        cur.next.next = tmp.next;
                        tmp.next = null;
                    }else {
                        if(pre != null){
                            //删除cur和cur.next
                            pre.next = cur.next.next;
                            if(pre.next != null){
                                cur = pre.next;
                            }else {
                                break;
                            }
                        }else {
                            cur = cur.next.next;
                            if(cur.next == null){
                                if(pre != null){
                                    pre.next = cur.next;
                                    return res;
                                }else {
                                    return cur;
                                }
                            }
                        }
                    }
                }else {
                    //后面没节点 直接消掉这两个
                    if(pre != null){
                        pre.next = null;
                        break;
                    }else {
                        return null;
                    }
                }
            }else {
                pre = cur;
                if(res == null){
                    res = cur;
                }
                cur = cur.next;
            }
        }

        return res;
    }

    /**
     * 递归是不是好点 起码边界清晰的很
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        if(head.val == head.next.val){
            ListNode cur = head;
            while (cur != null && cur.val == head.val){
                cur = cur.next;
            }
            return deleteDuplicates2(cur);
        }else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;
    }

    public static void main(String[] args) {
        LeetCode82 leetCode82 = new LeetCode82();
        ListNode head = ListNode.initTreeByLoop(new int[]{1,1,2});
        leetCode82.deleteDuplicates(head);
    }
}
