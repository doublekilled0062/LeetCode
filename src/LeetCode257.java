import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, null, res);
        return res;
    }

    private void dfs(TreeNode root, String str, List<String> res){
        if(root == null){
            return;
        }
        String temp = str == null ? String.valueOf(root.val) : str + "->" + root.val;
        if(root.left == null && root.right == null){
            res.add(temp);
            return;
        }
        if(root.left != null){
            dfs(root.left, temp, res);
        }
        if(root.right != null){
            dfs(root.right, temp, res);
        }
    }
}
