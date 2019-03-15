import java.util.LinkedList;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 *
 * 给出一个完全二叉树，求出该树的节点个数。
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 *
 * 示例:
 * 输入:
 *                  1
 *                 / \
 *                2   3
 *               / \  /
 *              4  5 6
 * 输出: 6
 */
public class LeetCode222 {
    /**
     * 完全二叉树 = 根 + 满二叉树 + 完全二叉树
     * 满二叉树的节点数为 2^h - 1
     * 递归判断哪个子树为满二叉树
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if(leftHeight == rightHeight){
            //左右子树高度相同 则不满的一定再右边
            return 1 + getFullTreeNodeNum(leftHeight) + countNodes(root.right);
        }else {
            return 1 + countNodes(root.left) + getFullTreeNodeNum(rightHeight);
        }
    }

    /**
     * 完全二叉树的高度可以直接通过最左节点去找
     * @param root
     * @return
     */
    private int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int height = 1;
        TreeNode node = root;
        while (node.left != null){
            node = node.left;
            height++;
        }
        return height;
    }

    /**
     * 返回满二叉树的节点个数
     * @param height
     * @return
     */
    private int getFullTreeNodeNum(int height){
        if(height == 0){
            return 0;
        }
        return (1 << height) - 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;

        LeetCode222 leetcode332 = new LeetCode222();
        System.out.println(leetcode332.countNodes(node1));
    }
}
