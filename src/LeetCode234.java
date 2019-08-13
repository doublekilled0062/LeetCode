/**
 * 234. 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode234 {
    /**
     * 先双指针找中点 然后把另一半反转 判断相等
     * 不知道 但是不知道需不需要最后把链表复原
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //都是从slow.next开始判断是不是回文
        ListNode mid = slow.next;
        slow.next = null;
        //反转从mid开始到结束
        ListNode pre = mid;
        ListNode cur = mid.next;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        mid.next = null;
        //从head和pre开始判断是否相等
        ListNode p1 = head;
        ListNode p2 = pre;
        boolean result = true;
        while (p2 != null){
            if(p1.val != p2.val){
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        //恢复原来链表
        ListNode pre1 = pre;
        ListNode cur1 = pre.next;
        while (cur1 != null){
            ListNode next = cur1.next;
            cur1.next = pre1;
            pre1 = cur1;
            cur1 = next;
        }
        pre.next = null;
        slow.next = pre1;

        return result;
    }

    public static void main(String[] args) {
        LeetCode234 leetCode234 = new LeetCode234();
        System.out.println(leetCode234.isPalindrome(ListNode.initTreeByLoop(new int[]{1,2,2,1})));
        System.out.println(leetCode234.isPalindrome(ListNode.initTreeByLoop(new int[]{1,2,3,2,1})));
        System.out.println(leetCode234.isPalindrome(ListNode.initTreeByLoop(new int[]{1,2,3,1,1})));
        System.out.println(leetCode234.isPalindrome(ListNode.initTreeByLoop(new int[]{1,2,1,1})));
    }
}
