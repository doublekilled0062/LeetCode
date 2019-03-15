import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 返回它的最大深度 3 。
 */
public class LeetCode104 {
    /**
     * 深度的概念有的地方是节点个数有的地方是边的个数
     * 递归方法
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 非递归方式
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }

        if(root.left == null && root.right == null){
            return 1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int queueLen = queue.size();
        int height = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            queueLen--;
            TreeNode left = node.left;
            TreeNode right = node.right;
            if(left != null){
                queue.offer(left);
            }
            if(right != null){
                queue.offer(right);
            }
            if(queueLen == 0 && !queue.isEmpty()){
                height++;
                queueLen = queue.size();
            }
        }
        return height;
    }

    /**
     * 官方解答 模拟栈 但是Pair为非标准类库 会运行不成功
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }
}
