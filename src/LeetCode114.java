import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 *
 * 给定一个二叉树，原地将它展开为链表。
 * 例如，给定二叉树
 *             1
 *            / \
 *           2   5
 *          / \   \
 *         3   4   6
 *
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LeetCode114 {
    /**
     * 辅助栈深度优先遍历
     * @param root
     */
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(pre != null){
                pre.right = node;
                pre.left = null;
            }
            pre = node;
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }

    /**
     * 递归，但是要遍历左子树找到尾节点 然后尾节点的右为右子树 跟的右为左子树 左子树置空
     * @param root
     */
    public void flatten2(TreeNode root) {
        if(root == null){
            return;
        }
        flatten2(root.left);
        flatten2(root.right);
        if(root.left != null){
            TreeNode p = root.left;
            while (p.right != null){
                p = p.right;
            }
            p.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
}
