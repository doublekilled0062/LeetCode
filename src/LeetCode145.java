import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *          1
 *           \
 *           2
 *          /
 *         3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

public class LeetCode145 {
    /**
     * 递归方式
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root != null){
            result.addAll(postorderTraversal(root.left));
            result.addAll(postorderTraversal(root.right));
            result.add(root.val);
        }
        return result;
    }

    /**
     * 循环方式
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                if(node.left != null){
                    node = node.left;
                }else {
                    node = node.right;
                }
            }else {
                node = stack.pop();
                if(node.left != null && node.right != null){
                    //替换一下 防止重复遍历
                    TreeNode temp = new TreeNode(node.val);
                    stack.push(temp);
                    node = node.right;
                }else {
                    result.add(node.val);
                    node = null;
                }
            }
        }
        return result;
    }

    /**
     * 循环方式 保留一个上次的指针
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if((node.left == null && node.right == null) ||
                    pre != null && (pre == node.left || pre == node.right)){
                result.add(node.val);
                pre = node;
                stack.pop();
            }else {
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
        return result;
    }

    /**
     * 循环方式 逆序更新
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(0, node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        return result;
    }


}
