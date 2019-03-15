import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层次遍历
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *                   3
 *                  / \
 *                 9  20
 *                 /  \
 *                15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int nodeLen = 1;
        List<Integer> nodeList = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            nodeList.add(node.val);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            nodeLen--;
            if(nodeLen == 0){
                result.add(nodeList);
                nodeLen = queue.size();
                nodeList = new ArrayList<>();
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> nodeList = new ArrayList<>();
            int count = queue.size();
            while (count > 0){
                TreeNode node = queue.poll();
                nodeList.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                count--;
            }
            result.add(nodeList);
        }
        return result;
    }
}
