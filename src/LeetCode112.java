import java.util.Stack;

/**
 * 112. 路径总和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *                    5
 *                   / \
 *                  4   8
 *                 /   / \
 *                11  13  4
 *               /  \      \
 *              7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class LeetCode112 {
    /**
     * 递归方式
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 非递归方式
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        int total = 0;
        while (!stack.isEmpty()){
            TreeNode curNode = stack.peek();
            total = total + curNode.val;
            if(pre != null && (curNode.left == pre || curNode.right == pre)){
                stack.pop();
                pre = curNode;
                total = total - curNode.val;
                continue;
            }
            if(curNode.left == null && curNode.right == null){
                if(total == sum){
                    return true;
                }else {
                    stack.pop();
                    pre = curNode;
                    total = total - curNode.val;
                }
            }else {
                if(curNode.right != null){
                    stack.push(curNode.right);
                }
                if(curNode.left != null){
                    stack.push(curNode.left);
                }
            }
        }
        return false;
    }

    /**
     * 非递归方式
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum3(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if(curNode.left == null && curNode.right == null){
                if(sum == curNode.val){
                    return true;
                }
            }
            if(curNode.left != null){
                curNode.left.val += curNode.val;
                stack.push(curNode.left);
            }
            if(curNode.right != null){
                curNode.right.val += curNode.val;
                stack.push(curNode.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.initTreeByLoop(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        LeetCode112 leetcode112 = new LeetCode112();
        System.out.println(leetcode112.hasPathSum3(node, 22));
    }
}
