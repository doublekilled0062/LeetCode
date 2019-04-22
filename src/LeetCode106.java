import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *            3
 *           / \
 *          9  20
 *            /  \
 *          15   7
 *
 */
public class LeetCode106 {
    /**
     * 一个普通的二叉树递归 但是题目假设了 没有重复元素 所以开始放map保存索引备查会极大提高效率
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length == 0){
            return null;
        }

        Map<Integer, Integer> posMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            posMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, posMap);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> posMap) {
        if(inStart > inEnd){
            return null;
        }
        if(postStart == postEnd){
            //只有一个元素了 就是跟节点
            return new TreeNode(postorder[postEnd]);
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = posMap.get(postorder[postEnd]);
        //左子树
        root.left = buildTree(inorder, inStart, index - 1, postorder, postStart, postStart + index - 1 - inStart, posMap);
        //右子树
        root.right = buildTree(inorder, index + 1, inEnd, postorder, postEnd - 1 - inEnd + index + 1, postEnd - 1, posMap);
        return root;
    }

    public static void main(String[] args) {
        LeetCode106 leetCode106 = new LeetCode106();
        leetCode106.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
    }
}
