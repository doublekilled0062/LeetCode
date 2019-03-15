/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 示例:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *          5
 *         / \
 *        3   6
 *       / \   \
 *      2   4   7
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *          5
 *         / \
 *        4   6
 *       /     \
 *      2       7
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *         5
 *        / \
 *       2   6
 *       \   \
 *       4   7
 */
public class LeetCode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        //先找到key所在的节点和父节点
        TreeNode p = root;
        TreeNode preP = null;

        while (p != null){
            if(key < p.val){
                preP = p;
                if(p.left != null){
                    p = p.left;
                }else {
                    p = null;
                }
            }else if(key > p.val){
                preP = p;
                if(p.right != null){
                    p = p.right;
                }else {
                    p = null;
                }
            }else {
                break;
            }
        }
        //没找到节点
        if(p == null){
            return root;
        }
        //如果p的两个左右子树都存在，把p节点与p的右子树最小值节点互换，然后删除最小值节点
        if(p.left != null && p.right != null){
            TreeNode minP = p.right;
            TreeNode minPreP = p;
            while (minP.left != null){
                minPreP = minP;
                minP = minP.left;
            }
            p.val = minP.val;
            //替换一下 变成删除这个替换的节点
            p = minP;
            preP = minPreP;
        }
        //p肯定只有一个节点 preP为空表示删除根节点 TODO
        if(p.left == null && p.right == null){
            if(preP == null){
                return null;
            }else if(preP.left == p){
                preP.left = null;
            }else {
                preP.right = null;
            }
        }else if(p.left != null){
            if(preP == null){
                return p.left;
            }else if(preP.left == p){
                preP.left = p.left;
            }else {
                preP.right = p.left;
            }
        }else {
            if(preP == null){
                return p.right;
            }else if(preP.left == p){
                preP.left = p.right;
            }else {
                preP.right = p.right;
            }
        }
        return root;
    }
}
