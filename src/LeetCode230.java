import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *            3
 *           / \
 *          1   4
 *          \
 *          2
 * 输出: 1
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *            5
 *           / \
 *          3   6
 *         / \
 *        2   4
 *       /
 *      1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 */
public class LeetCode230 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()){
            if(curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }else {
                curNode = stack.pop();
                k--;
                if(k == 0){
                    return curNode.val;
                }
                curNode = curNode.right;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node3.left = node1;
        node3.right = node4;

        node1.right = node2;

        LeetCode230 leetcode230 = new LeetCode230();
        System.out.println(leetcode230.kthSmallest(node3, 1));
    }
}
