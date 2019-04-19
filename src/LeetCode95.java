import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *       1         3     3      2      1
 *        \       /     /      / \      \
 *         3     2     1      1   3      2
 *        /     /       \                 \
 *       2     1         2                 3
 */
public class LeetCode95 {
    /**
     * 看起来也是回溯
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0){
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        if(start == end){
            List<TreeNode> list = new ArrayList<>();
            TreeNode node = new TreeNode(start);
            list.add(node);
            return list;
        }
        //已mid为根节点 左右子树分别为start到mid-1 和 mid+1到end 注意边界
        List<TreeNode> res = new ArrayList<>();
        for(int mid = start; mid <= end; mid++){
            List<TreeNode> left = new ArrayList<>();
            List<TreeNode> right = new ArrayList<>();
            if(start != mid){
                //说明有左子树
                left = generateTrees(start, mid - 1);
            }
            if(end != mid){
                //说明有右子树
                right = generateTrees(mid + 1, end);
            }
            if(!left.isEmpty() && !right.isEmpty()){
                for(TreeNode l : left){
                    for(TreeNode r : right){
                        TreeNode root = new TreeNode(mid);
                        root.left = l;
                        root.right = r;
                        res.add(root);
                    }
                }
            }else if(!left.isEmpty()){
                for(TreeNode l : left){
                    TreeNode root = new TreeNode(mid);
                    root.left = l;
                    res.add(root);
                }
            }else if(!right.isEmpty()){
                for(TreeNode r : right){
                    TreeNode root = new TreeNode(mid);
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
