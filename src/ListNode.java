/**
 * 链表和数组初始化链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 递归
     * @param values
     * @param index
     * @return
     */
    public static ListNode initTreeByRecursive(int[] values, int index){
        if(values == null || values.length == 0 || index < 0 || index >= values.length){
            return null;
        }
        ListNode node = new ListNode(values[index]);
        node.next = ListNode.initTreeByRecursive(values, index + 1);
        return node;
    }

    public static ListNode initTreeByLoop(int[] values){
        if(values == null || values.length == 0){
            return null;
        }
        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        for(int i = 1; i < values.length; i++){
            ListNode next = new ListNode(values[i]);
            cur.next = next;
            cur = next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 6, 8, 10};
        ListNode head1 = ListNode.initTreeByRecursive(nums, 5);
        ListNode head2 = ListNode.initTreeByLoop(nums);
        System.out.println("head1 = ");
        while (head1 != null){
            System.out.print(head1.val + " ");
            head1 = head1.next;
        }
        System.out.println();
        System.out.println("head2 = ");
        while (head2 != null){
            System.out.print(head2.val + " ");
            head2 = head2.next;
        }
    }
}
