import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *             0
 *            / \
 *         -3   9
 *         /   /
 *       -10  5
 */
public class LeetCode109 {
    /**
     * 快慢针找中点 然后递归么
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return new TreeNode(head.val);
        }
        ListNode midPre = getMidPre(head);
        ListNode mid = midPre.next;
        midPre.next = null;
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    private ListNode getMidPre(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode midPre = null;
        while (fast != null && fast.next != null){
            midPre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return midPre;
    }

    /**
     * 快慢针的是不如不遍历链表把元素当数组存下来
     * 然后用leetCode108的做法
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if(head == null){
            return null;
        }
        List<Integer> nums = new ArrayList<>();
        while (head != null){
            nums.add(head.val);
            head = head.next;
        }
        return  sortedArrayToBST(nums.toArray(new Integer[nums.size()]));
    }

    private TreeNode sortedArrayToBST(Integer[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        if(nums.length == 1){
            return new TreeNode(nums[0]);
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(Integer[] nums, int start, int end) {
        if(start > end){
            return null;
        }
        if(start == end){
            return new TreeNode(nums[start]);
        }
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        if(mid == start){
            root.right = new TreeNode(nums[end]);
        }else {
            root.left = sortedArrayToBST(nums, start, mid - 1);
            root.right = sortedArrayToBST(nums, mid + 1, end);
        }
        return root;
    }

    public static void main(String[] args) {
        LeetCode109 leetCode109 = new LeetCode109();
        leetCode109.sortedListToBST2(ListNode.initTreeByLoop(new int[]{-10, -3, 0, 5, 9}));
    }

}
