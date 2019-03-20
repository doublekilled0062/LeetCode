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
        TreeNode node = TreeNode.initTreeByLoop(new Integer[]{3, 1, 4, null, 2});
        LeetCode230 leetcode230 = new LeetCode230();
        System.out.println(leetcode230.kthSmallest(node, 1));
    }
}
