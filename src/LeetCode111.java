import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *                   3
 *                  / \
 *                 9  20
 *                   /  \
 *                  15   7
 * 返回它的最小深度  2.
 */
public class LeetCode111 {
    /**
     * 非递归
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        int depth = 0;
        if(root == null){
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            depth++;
            int count = queue.size();
            while (count > 0){
                TreeNode node = queue.poll();
                //左子树和右子树都空时才停止
                if(node.left == null && node.right == null){
                    return depth;
                }
                //只有一个节点的话 最小深度要继续往下找
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                count--;
            }
        }
        return depth;
    }

    /**
     * 递归方式
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }else if(root.left == null && root.right != null){
            return 1 + minDepth2(root.right);
        }else if(root.left != null && root.right == null){
            return 1 + minDepth2(root.left);
        }else {
            return 1 + Math.min(minDepth2(root.left), minDepth2(root.right));
        }
    }

    /**
     * 简洁方式
     * @param root
     * @return
     */
    public int minDepth3(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        if(left != 0 && right != 0){
            return 1 + Math.min(left, right);
        }else {
            return 1 + left + right;
        }
    }
}
