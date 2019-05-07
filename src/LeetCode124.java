import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 124. 二叉树中的最大路径和
 *
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1:
 * 输入: [1,2,3]
 *          1
 *         / \
 *        2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *         -10
 *         / \
 *        9  20
 *          /  \
 *         15   7
 * 输出: 42
 */
public class LeetCode124 {

    /**
     * 求根节点的所有路径和 然后往下递归求最大值
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if(root == null){
            return Integer.MIN_VALUE;
        }
        int left = maxPathSumRoot(root.left);
        int right = maxPathSumRoot(root.right);
        int max = Integer.MIN_VALUE;
        if(left > 0 && right > 0){
            if(root.val + left + right > max){
                max = root.val + left + right;
            }
        }else if(left > 0){
            if(root.val + left > max){
                max = root.val + left;
            }
        }else if(right > 0){
            if(root.val + right > max){
                max = root.val + right;
            }
        }else {
            if(root.val > max){
                max = root.val;
            }
        }
        max = Math.max(max, Math.max(maxPathSum(root.left), maxPathSum(root.right)));
        return max;
    }

    /**
     * 返回从根节点到某个节点的最大路径和
     * @param root
     * @return
     */
    public int maxPathSumRoot(TreeNode root){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        int total = 0;
        int max = Integer.MIN_VALUE;
        while (!stack.isEmpty()){
            TreeNode curNode = stack.peek();
            if(pre != null && (curNode.left == pre || curNode.right == pre)){
                stack.pop();
                pre = curNode;
                total = total - curNode.val;
                continue;
            }
            total = total + curNode.val;
            if(total > max){
                max = total;
            }
            if(curNode.left == null && curNode.right == null){
                stack.pop();
                pre = curNode;
                total = total - curNode.val;
                continue;
            }else {
                if(curNode.right != null){
                    stack.push(curNode.right);
                }
                if(curNode.left != null){
                    stack.push(curNode.left);
                }
            }
        }
        return max;
    }

    private int max = Integer.MIN_VALUE;

    /**
     * 应该直接递归就可以
     * @param root
     * @return
     */
    public int maxPathSum2(TreeNode root) {
        if(root == null){
            return Integer.MIN_VALUE;
        }
        getMaxSum(root);
        return max;

    }

    /**
     * 包含根节点的路径和
     * @param root
     * @return
     */
    public int getMaxSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = Math.max(0, getMaxSum(root.left));
        int right = Math.max(0, getMaxSum(root.right));
        max = Math.max(max, root.val + left + right);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        LeetCode124 leetCode124 = new LeetCode124();
        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1})));
        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{1,2,3})));
        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{1,-2,-3,1,3,-2,null,-1})));
        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{-3})));

        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{4,11,null,7,2})));
        System.out.println(leetCode124.maxPathSumRoot(TreeNode.initTreeByLoop(new Integer[]{8,13,4,null,null,null,1})));

        System.out.println(leetCode124.maxPathSum(TreeNode.initTreeByLoop(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1})));
        System.out.println(leetCode124.maxPathSum(TreeNode.initTreeByLoop(new Integer[]{1,2,3})));
        System.out.println(leetCode124.maxPathSum(TreeNode.initTreeByLoop(new Integer[]{1,-2,-3,1,3,-2,null,-1})));
        System.out.println(leetCode124.maxPathSum(TreeNode.initTreeByLoop(new Integer[]{-3})));
    }
}
