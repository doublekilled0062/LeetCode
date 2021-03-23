/**
 * 203. 移除链表元素
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class LeetCode203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode p = head;
        while (p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int n = 0;
        for(int i = 2; i < 3000; i++){
            if(i % 2 == 0 || i % 3 == 0 || i % 5 == 0){
                n++;
                if(n == 1500){
                    System.out.println(i);
                }
            }
        }
    }
}
