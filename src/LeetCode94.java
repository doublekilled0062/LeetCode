import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3

 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class LeetCode94 {
    /**
     * 递归方式
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));
        return result;
    }

    /**
     * 循环方式 todo
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                stack.add(treeNode);
                treeNode = treeNode.left;
            }else {
                TreeNode node = stack.pop();
                result.add(node.val);
                treeNode = node.right;
            }
        }
        return result;
    }

    /**
     * 上面循环方式其实来自Morris遍历算法
     * 1 根据当前节点，找到其前序节点，如果前序节点的右孩子是空，那么把前序节点的右孩子指向当前节点，然后进入当前节点的左孩子。
     * 2 如果当前节点的左孩子为空，打印当前节点，然后进入右孩子。
     * 3 如果当前节点的前序节点其右孩子指向了它本身，那么把前序节点的右孩子设置为空，打印当前节点，然后进入右孩子。
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            } else {
                //前面判断保证一定有左孩子 所以前驱节点一定在左孩子或者左孩子的底右孩子 而不是空或者其父节点
                TreeNode pre = getPredecessor(node);
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                }else if (pre.right == node) {
                    pre.right = null;
                    res.add(node.val);
                    node = node.right;
                }
            }
        }
        return res;
    }

    /**
     * 获得有左孩子的节点的 前驱节点
     * pre的right有特殊处理 会指向root 所以要判断一下
     * @param root
     * @return
     */
    private TreeNode getPredecessor(TreeNode root) {
        TreeNode pre = null;
        if (root.left != null) {
            pre = root.left;
            while (pre.right != null && pre.right != root) {
                pre = pre.right;
            }
        }
        return pre;
    }
}
