import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \    / \
 *        7    2  5   1
 * 返回:
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class LeetCode113 {
    /**
     * 深度优先遍历
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    public void dfs(TreeNode root, int sum, List<Integer> temp, List<List<Integer>> result){
        if(root.left == null && root.right == null && root.val == sum){
            List<Integer> list = new ArrayList<>(temp);
            list.add(root.val);
            result.add(list);
            return;
        }
        if(root.left != null){
            temp.add(root.val);
            dfs(root.left, sum - root.val, temp, result);
            temp.remove(temp.size() - 1);
        }
        if(root.right != null){
            temp.add(root.val);
            dfs(root.right, sum - root.val, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode113 leetCode113 = new LeetCode113();
        leetCode113.pathSum(TreeNode.initTreeByLoop(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22);
    }
}
