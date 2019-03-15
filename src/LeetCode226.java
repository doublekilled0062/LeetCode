import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *                   4
 *                 /   \
 *                2     7
 *               / \   / \
 *              1   3 6   9
 * 输出：
 *                   4
 *                 /   \
 *                7     2
 *               / \   / \
 *              9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */
public class LeetCode226 {
    /**
     * 递归啊递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 非递归实现
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            //交换节点
            if(node.left != null || node.right != null){
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }
        }
        return root;
    }
}
